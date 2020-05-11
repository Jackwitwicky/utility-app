# Utilities App

Introducing Utilities App, and Android based application to help you organize, pay and track your bills all in one beautifully designed place. Say goodbye to scribbling
your 10 digit electricity meter number on a piece of paper as you pay via MPESA, or calling your TV subscription company a third time in a row to ask what your account number is.
Using Utilities App, simply create a new bill and save all key details about it. This allows you pay via MPESA in the click of button.


Splash Screen             |  Home Screen
:-------------------------:|:-------------------------:
![](https://user-images.githubusercontent.com/8895134/81540466-60599c00-937a-11ea-97f6-7a2b0237652f.png)  |  ![](https://user-images.githubusercontent.com/8895134/81540617-93039480-937a-11ea-99b8-0637f2188f38.png)

Add Bill Screen             |  Confirm Transaction
:-------------------------:|:-------------------------:
![](https://user-images.githubusercontent.com/8895134/81540905-03aab100-937b-11ea-9ce8-23377fe05055.png)  | ![](https://user-images.githubusercontent.com/8895134/81540883-fd1c3980-937a-11ea-89d8-8d034bb56454.png)

Successful Transaction
:-------------------------:|:-------------------------:
![](https://user-images.githubusercontent.com/8895134/81540896-01485700-937b-11ea-85cc-5d59b8ef6f2b.png)  |


## Getting Started

To get the application running, either

### Build from source

#### Prerequisites

What things you need to build the app and how to install them

```
Android Studio Version 3.6 or higher

Minimum sdk used :- 18

```

Simply clone the repo, open it in your Android Studio and after dependencies have been downloaded, click play to run
it on a connected device

### Download from Google Play

This is still in the works. Hope to make it available soon

## How it Works

The app utilizes [Hover library](https://www.usehover.com/) to automate the Sim Toolkit process for inputting values to initiate an MPESA transaction.
After launching the app, the user can use the **Add Bill** form to create a new bill. Utility app ships with payment details for some Common
Utility providers such as KPLC, Nairobi Water, Go TV, Star times and Zuku. However if the user can create a new Utility provider and specify their details.

When a user wants to pay for a particular utility, clicking the **PAY** button from the home screen will initiate the process while pre-filling all the payment
details of the bill and requesting the user for their MPESA pin, after which the process will be completed and the app will show a success dialog to the user.


## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.

## Authors

 **Jack Kiarie** - [Other Works](https://github.com/Jackwitwicky)

 **Zephaniah Sefu** - [Other Works](https://github.com/SefuZeph)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.


