import vk
from datetime import datetime
import json
import time


class ParseItem:
  def __init__(self, id, deep, parentId):
    self.deep = deep
    self.id = id
    self.parentId = parentId

class User:
    def toJSON(self):
        return json.dumps(self, default=lambda o: o.__dict__, 
            sort_keys=True, indent=4)
    
    def __init__(self, id, age, countPhoto, countVideo, countNotes, countGroups):
        self.id = id
        self.age = age
        self.photo = countPhoto
        self.countVideo = countVideo
        self.countNotes = countNotes
        self.countGroups = countGroups

def ParseUserFriends(Id, deep):
    if deep < max_deep:
        try:
            deep += 1
            friends = vk_api.friends.get(user_id=Id, order='hints', v = 5.101)
            mapItems = map(lambda fr: ParseItem(fr, deep, Id) , friends['items'])
            parseItems = list(mapItems)
        except:
            return

        parsedUsers.extend(parseItems)
        for item in parseItems:
            ParseUserFriends(item.id, item.deep)
    else:
        return

def GetUserInfoById(Id):
    try:
        userInfo = vk_api.users.get(user_id=Id, fields='bdate, counters', v = 5.101)
        
        try:
            datestr = userInfo[0]['bdate']
            now = datetime.now()
            birthday = datetime.strptime(datestr, '%d.%m.%Y')
            age = now.year - birthday.year
        except:
            age = 0

        try:
            photos = userInfo[0]['counters']['photos']
        except:
            photos = 0

        try:
            videos = userInfo[0]['counters']['videos']
        except:
            videos = 0

        try:
            notes = userInfo[0]['counters']['notes']
        except:
            notes = 0
        try:
            groups = userInfo[0]['counters']['groups']
        except:
            groups = 0

        return User(Id, age, photos, videos, notes, groups)

    except Exception as er:
        print(er)
        return


session = vk.Session(access_token='c36d831e3924cda20091a63c9ab16243e43fcc52d780bbe8734b2557102ed9768262a39efcc2b7251febf')
vk_api = vk.API(session)
max_deep = 2
startId = 177991274
parsedUsers = []
usersInfo = []

ParseUserFriends(startId, 0)

count=0

for user in parsedUsers:
    if count % 5 != 0:
        us = GetUserInfoById(user.id)
        count += 1
        if us is not None:
            usersInfo.append(us)
        print(count)
    else:
        time.sleep(1)
        print("sleep")
        count += 1


with open('data.json', 'w') as outfile:
    json.dump(usersInfo, outfile, default=lambda x: x.__dict__)

