import web
import json
from firebase import firebase



#theFirebase = firebase.FirebaseApplication('https://curo.firebaseio.com/')

#rawJson = theFirebase.get('/', None)
#alerts = theFirebase.get('/alerts/', None)
#areas = theFirebase.get('/areas/', None)
#tasks = theFirebase.get('tasks/', None)

#overseerLocation = theFirebase.get('/overseer/location/', None)

##taskDict = dict
#tasks = json.dumps(tasks)
#alerts = json.dumps(alerts)
#taskDict = json.loads(tasks)
#alertDict = json.loads(alerts)
#overseerLocation = json.dumps(overseerLocation)
#overseerLocation = json.loads(overseerLocation)



urls = (
    '/', 'index'
)

render = web.template.render('templates/')

class index:


    def __init__(self):
        pass

    def GET(self):
        #if not taskDict:
        #    print "taskDict empty"

        #if not alertDict:
        #    print "alertDict empty"

        #if not overseerLocation:
        #    print "overseerLocation empty"


        return render.index() #asks, alerts)

if __name__ == "__main__":
    app = web.application(urls, globals())
    app.run()

class task:
    TASK_NOTSTARTED = -1
    TASK_STARTED = 0
    TASK_DONE = 1


    desc = ""
    locationId = -1
    time = "0100:1000"
    completion = TASK_NOTSTARTED


    def __init__(self, desc, locationID, time, completion):
        self.desc = desc
        self.locationID = locationID
        self.time = time
        self.completion = completion