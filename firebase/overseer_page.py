import web
from firebase import firebase

firebase = firebase.FirebaseApplication('https://curo.firebaseio.com/')
result = firebase.get('/Tasks', None)

urls = (
    '/', 'index'
)

class index:
    def GET(self):
        return result

if __name__ == "__main__":
    app = web.application(urls, globals())
    app.run()