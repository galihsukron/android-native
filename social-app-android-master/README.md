SocialComponents
=============

## Summary
This application is a pattern of social components comprising posts, likes, comments, views, and others that can be used for Android application development. It is Firebase-based  and uses such Firebase features as the database, storage, and authentication. Our app has Firebase queries implemented in it, i.e. data pagination, filtering data by an author. As well, it is the example of working with Firebase transaction for counting likes, views, and comments. Our application is the great example of material design. The user-friendly animation guides a person by opening screens of details and a user profile.



Setup steps
===========

 1. First of all you need google-services.json. Create a Firebase project in the [Firebase console](https://console.firebase.google.com/), if you don't already have one. Go to your project and click ‘Add Firebase to your Android app’. Follow the setup steps. At the end, you'll download a google-services.json file which you should add to your project.

 ![google_service_json](https://user-images.githubusercontent.com/7821425/32899277-30da3374-caf3-11e7-86e0-58cb1bfd59e2.png)

 2. Setup realtime database. In firebase console go to DEVELOP->Database-> Get Started -> choose tab ‘RULES’ and past this:

 ```
 {
   "rules": {
     ".read": "true",
     ".write": "true"
   }
 }
 ```

 3. If you haven't yet specified your app's SHA-1 fingerprint, do so from the Settings page [Settings page](https://console.firebase.google.com/project/_/settings/general/) of the Firebase console. See [Authenticating Your Client](https://developers.google.com/android/guides/client-auth) for details on how to get your app's SHA-1 fingerprint.

 4. Enable the sign in method with google. Go to DEVELOP -> Authentication -> SIGN-IN METHODS. You will see Sign-in providers. Find Google and enable it.  Here you will see Web SDK configuration. Open it and copy Web client ID and put it in the project: /app/src/main/res/values/constants.xml to “google_web_client_id” property.

 ![google_web_client_id](https://user-images.githubusercontent.com/7821425/32899597-12302680-caf4-11e7-9169-650982c0334e.png)

 5. Enable facebook sign in method.
  - On the [Facebook for Developers](https://developers.facebook.com/) site, add new application. 
 
  - Get the App ID and an App Secret for your app. 
  
  - Go to DEVELOP -> Authentication -> SIGN-IN METHODS. 
  On the Sign in method tab, enable the Facebook sign-in method and specify the App ID and App Secret 
  you got from Facebook. There you can fined OAuth redirect URL (e.g. my-app-12345.firebaseapp.com/__/auth/handler). 
  You should use it on the next step.
   
  - Configure the Facebook Login on the [Facebook for Developers](https://developers.facebook.com/) site. 
  You don't need  add extra lines to the project from the instruction! All necessary data is already there.
  Define OAuth redirect URL you got from the firebase console on the previous step.
  
   - Finally, Put App ID from facebook to the project: /app/src/main/res/values/constants.xml to the “facebook_app_id” property.

 6. Init storage. Go to [Firebase console](https://console.firebase.google.com/), DEVELOP->Storage. Follow instructions on this page. At the end you should see the link. It is like “gs://test-9eff4.appspot.com”. Put this link to the project /app/src/main/res/values/constants.xml to “storage_link” property.

![storage_link_exp](https://user-images.githubusercontent.com/7821425/32899046-8811009c-caf2-11e7-905f-741174d26512.png)

 7. Deploy cloud functions
  - Set up and initialize Firebase SDK for Cloud Functions following the 
    [Firebase instruction](https://firebase.google.com/docs/functions/get-started#set_up_and_initialize).
   **Important! During initialization CLI firebase ask you to override package.json and index.js files. Do not override index.js file!** All cloud functions code from this repository are already there. Subsequently you can add new functions to this file.
  - Deploy the function by running the command: 
     ```
    $ firebase deploy --only functions
     ```
    

 Now you can install app, login and create a post.


