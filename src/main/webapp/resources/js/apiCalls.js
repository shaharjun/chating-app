sampleMessage = {
    'creator' : 'd@gmail.com',
    'receiver' : 'chandra@gmail.com',
    'chatMessageId' : 0,
    'createdOn' : ' ',
    'chatMessageText' : 'yolo',
    'chatStatus' : ' ',
    'chatType' : ' ',
    'ack' : 0
  }
function getCurrentUserEmail() {
    var user =null;
    user = getLocalStorage("thisUser");
    return user.emailId;
}

function createUser(user){   
    // store this user's data in local storage
    if(user!=null) {
    //user = JSON.parse(user);
    console.log("inside createUser");
    firebase.database().ref('allUsers/' + user.userId).set(user);
    //addUserToUserList(user);
    }
    // storing user's data in user list(local storage)
    
}

function addUserToUserList(user){
    var store = getLocalStorage("allUsers");
    if (store == null) {
        store = new Map();
        store[user.emailId] = user;
        setLocalStorage("allUsers",store);
    } else {
        store[user.emailId] = user;
        setLocalStorage("allUsers",store);
    }
}

function storeChat(message) {
    
    var thisUser = getLocalStorage("thisUser");
    firebase.database().ref('idGenerator/messageId').once('value').then(function(snapshot){
        message.messageId = snapshot.val();
        var id = message.messageId+1; 
        firebase.database().ref('idGenerator').update({'messageId':id}).then(function(){
           // thisUser.userId = 22;
            getAllUsers().then(function(data) {
            contact = getUser(data,message.receiver);
        //if(contact!=null) {
            firebase.database().ref('chatMessages/'+thisUser.userId+'/'+contact.userId+'/'+message.messageId).set(message);
            firebase.database().ref('chatMessages/'+contact.userId+'/'+thisUser.userId+'/'+message.messageId).set(message);
      //  }}
            });
   // });
});});
}

function getChatMessages(email) {
    var thisUser = getLocalStorage("thisUser");
    var msgs = null;
    return getAllUsers().then(function(data){
        contact = getUser(data,email);
        return firebase.database().ref('chatMessages/'+thisUser.userId+'/'+contact.userId)
        .once('value').then(function(snapshot){
            msgs =  snapshot.val();
            console.log(msgs);
            return msgs;
        })
    })
}

function storeStarMsg(starredMessage) {
    
    var thisUser = getLocalStorage("thisUser");

    var store = null;

    store = getLocalStorage("starredMessages");
    if(store == null) {
        store = new Map();
        var arr = [];
        arr.push(starredMessage);
        store[starredMessage.creator] = arr;
        setLocalStorage("starredMessages",store)
    } else {
        if (store[starredMessage.creator] != undefined) {
            var starArray = [];
            starArray = store[starredMessage.creator];
            starArray.push(starredMessage);
            store[starredMessage.creator] = starArray;
            setLocalStorage("starredMessages",store);
        } else {
            var starArray = [];
            starArray.push(starredMessage);
            store[starredMessage.creator] = starArray;
            setLocalStorage("starredMessages", store);
        }
    }
}

function getStarredMessages() {
    var starmsgs = null;

    starmsgs = getLocalStorage("starredMessages");
    return starmsgs;
}

function getRequests(userId) {
    
    return firebase.database().ref('contactRequests/'+userId).once('value').then(function(snapshot) {
        console.log(snapshot.val());
        return snapshot.val();
    });
   
}

function removeRequest(email) {
    var users = null;
    var id = getLocalStorage("thisUser").userId;
    var idToRemove;
    firebase.database().ref('contactRequests/'+id).once('value').then(function(snapshot) {
        var snapshotVal = snapshot.val();
        for(key in snapshotVal) {
            if(snapshotVal[key].sender == email)
            {
                idToRemove = key;
            }
        }
        firebase.database().ref('contactRequests/'+id+ '/'+idToRemove).remove();
        var el = $('#contacts > ul > li').eq($(event.currentTarget));
         el.remove();
         bringToTop($('#background'));
         location.reload();
    })
}

function getChatContacts() {
    var thisUser = getLocalStorage("thisUser");
    var id = thisUser.userId;
    return firebase.database().ref('allUsers/'+id+'/chatContacts').once('value').then(function(snapshot){
       console.log(snapshot.val());
        return snapshot.val();
    })
}
function addChatContact(email) { 
    var users = null;
    var id = getLocalStorage("thisUser").userId;
    var idToRemove;
    var userToAdd = {
        'userId': 0,
        'fullName' : '',
        'emailId' : ' '
    };
    var currUser = getLocalStorage("thisUser");
    return firebase.database().ref('contactRequests/'+id).once('value').then(function(snapshot) {
        var snapshotVal = snapshot.val();
        for(key in snapshotVal) {
            if(snapshotVal[key].sender == email)
            {
                idToRemove = key;
                userToAdd.emailId = snapshotVal[key].sender;
                userToAdd.fullName = snapshotVal[key].senderName;
                userToAdd.profilePictureUrl = snapshotVal[key].profilePictureUrlOfSender;
            }
        }
        firebase.database().ref('contactRequests/'+id+ '/'+idToRemove).remove();
        firebase.database().ref('allUsers/'+id+'/chatContacts/'+idToRemove).set(userToAdd);
        firebase.database().ref('allUsers/'+idToRemove+'/chatContacts/'+id).set(currUser);
        return userToAdd;
    })
}
// function getAllUsers() {
//     var users = null;

//     return firebase.database().ref('allUsers/').child('');
// }
function getAllContacts() {
    var thisUser = getLocalStorage("thisUser");
    firebase.database().ref('allUsers/'+thisUser.userId+'/chatContacts').once('value').then(function(snapshot){
        return snapshot.val();
    })
}
function getAllUsers() {
    //var def = $.Deferred();
    return firebase.database().ref('allUsers/').once('value').then(function(snapshot) {
        console.log(snapshot.val());
        return snapshot.val();
    });
    //return def.promise();
}
function getLoggedInUsers() {
    return firebase.database().ref('loggedInUser/').once('value').then(function(snapshot) {
        console.log(snapshot.val());
        return snapshot.val();
    });
}
function getUser(users,emailId) {
    for(var key in users) {
        if((users[key].emailId).indexOf(emailId) != -1) {
            console.log(users[key]);
            return users[key];
        }
    }
    return null;
}
function storeRequest(request) {
    var senderStatus = "offline";
    var senderObject;
    var receiverObject;
    var receiverStatus = 'offline';
    getLoggedInUsers().then(function(data) {
        console.log("in store request");
        console.log(data);
        receiverObject = getUser(data,request.receiver)
        if(receiverObject!=null) {
            receiverStatus = receiverObject.userStatus;
        }
        var picUrl = getLocalStorage("thisUser").profilePictureUrl;
        var creatorId = getLocalStorage("thisUser").userId;
        var name = getLocalStorage("thisUser").fullName;
        return firebase.database().ref().child('contactRequests').child(request.ack).child(creatorId).set({
            'sender' : request.creator,
            'senderName' : name,
            'requestStatus' : 'pending',
            'availabilityOfReceiver' : receiverStatus,
            'profilePictureUrlOfSender' : profilePictureUrl,
            'createdOn' : request.createdOn.toString()
        })
        return true;
    })
    return false;
}
function approveRequestHelper(){
    //add sender in receiver's contact list and add receiver in sender's contact list
    //also, remove request from contactRequest table

}
function declineRequest(){
    //remove request from contactRequest table and add them to declineRequest table
}