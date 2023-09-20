from sqlalchemy.exc import IntegrityError 
from models import SkiDay
from flask import request, jsonify

def initialize_routes(app):
    @app.route('/add_user', methods=['POST'])
    def add_user():
        try:
            data = request.get_json()
            new_user = User(username=data['username'], email=data['email'])
            db.session.add(new_user)
            db.session.commit()
            return jsonify({"message": "User added"}), 201
        except KeyError:
            return jsonify({"error": "Missing required data"}), 400
        except IntegrityError:
            db.session.rollback()
            return jsonify({"error": "Username or email already exists"}), 409

    @app.route('/add_ski_day', methods=['POST'])
    def add_ski_day():
        try:
            data = request.get_json()
            ski_day = SkiDay(
                user_id=data['user_id'],
                date=data.get('date', datetime.utcnow()),
                location=data['location'],
                hours_skied=data['hours_skied']
            )
            db.session.add(ski_day)
            db.session.commit()
            return jsonify({"message": "Ski day added"}), 201
        except KeyError:
            return jsonify({"error": "Missing required data"}), 400
        except IntegrityError:
            db.session.rollback()
            return jsonify({"error": "User ID does not exist"}), 404

    @app.route('/get_ski_days/<int:user_id>', methods=['GET'])
    def get_ski_days(user_id):
        ski_days = SkiDay.query.filter_by(user_id=user_id).all()
        if not ski_days:
            return jsonify({"message": "No ski days found for this user"}), 404
        return jsonify([{
            'date': ski_day.date.strftime('%Y-%m-%d'),
            'location': ski_day.location,
            'hours_skied': ski_day.hours_skied
        } for ski_day in ski_days]), 200
