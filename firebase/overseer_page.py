import web
import json
from firebase import firebase

firebase = firebase.FirebaseApplication('https://curo.firebaseio.com/')

#rawJson = firebase.get('/', None)
#alerts = firebase.get('/alerts', None)
#areas = firebase.get('/areas', None)
#tasklist = firebase.get('/task', None)



urls = (
    '/', 'index'
)

render = web.template.render('templates/')

class index:

    def __inti__(self):
        pass

    def GET(self):
        return render.index()

if __name__ == "__main__":
    app = web.application(urls, globals())
    app.run()