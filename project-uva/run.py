from flask import Flask
from models import db
from routes import initialize_routes

app = Flask(__name__)
app.config.from_object('config')

db.init_app(app)

@app.before_request
def before_request():
    db.create_all()

initialize_routes(app)

if __name__ == '__main__':
    app.run(debug=True)
