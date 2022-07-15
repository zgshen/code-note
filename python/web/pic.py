import flask
from flask import Response
import random

app = flask.Flask(__name__)


@app.route("/api", methods=['get'])
def api():
    return '''Self-use API.  
                </br> 
                Use /api/pic to get a backgroud image.'''


@app.route("/api/pic", methods=['post', 'get'])
def index():
    num = random.randint(1, 10)

    path = "/media/nathan/win-d/File/photo/comic/comic%s.png" % num

    resp = Response(open(path, 'rb'), mimetype="image/jpeg")
    return resp


app.run(host='0.0.0.0', port=12888, debug=False)
