from run import app, db  # Import your Flask app and SQLAlchemy instance
from models import User, SkiDay  # Import your models

# Create an application context to work with the database
app.app_context().push()

def add_mock_users():
    users = [
        User(username='user1', email='user1@example.com'),
        User(username='user2', email='user2@example.com'),

    ]

    db.session.bulk_save_objects(users)
    db.session.commit()

def add_mock_ski_days():
    ski_days = [
        SkiDay(user_id=1, location='Ski Resort A', hours_skied=5),
        SkiDay(user_id=1, location='Ski Resort B', hours_skied=6),
        SkiDay(user_id=2, location='Ski Resort C', hours_skied=4),
    ]

    db.session.bulk_save_objects(ski_days)
    db.session.commit()

if __name__ == '__main__':
    add_mock_users()
    add_mock_ski_days()
