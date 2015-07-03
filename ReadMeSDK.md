# 9Chau SDK integration for Game

##### Ver. 1.1.0 (22/06/2015)



###Table of Contents
[1. Introduction](#a)

[2. Terminology](#a)

[3. Mind map and Functions](#a)
    
[3.1. Authentication](#a)

[3.2. Payment](#a)
    
[4. Requirements](#a)
    
[4.1. Development Environment](#a)
    
[5. Important Notes](#a)

[6. How to integrate SDK](#a)

[6.1. Import 9chauSDK](#a)

[6.2. Config project](#a)

[6.3. Add new BroadcastReceiver](#a)

[6.4. Initialize SDK](#a)

[6.5. Add authentication function](#a)

[6.6. Add payment function](#a)

[6.7. Add profile function](#a)




### [1. Introduction](#header1)
This guide is intended to help you integrating SDK for Game. Please read this guide carefully while integrating.

### [2. Terminology](#header1)
- A 9Chau account is an account in 9Chau system.
- A game account, user must register and login before play game.
- A Telco is telecom company: Viettel, Vinaphone, Mobiphone, …
    - A Telco card contain two code: serial and pin code. User must enter completely and accurately when using payment function.
- A transaction is a game order when user recharge to a game.
    - A transaction ID is unique order ID for each game order.

### [3. Mind map and Functions](#header1)

9Chau SKD contain tree major components: Authentication, Payment, Profile Management.


##### [3.1. Authentication](#header1): Before playing game, user must pass by SDK authentication.

	a) If user want to play game immediately, they can choose Play now function. SDK create a random UID for this device, this UID is unique ID on this device. Each device only create one UID and user still use this UID for each other play time.

	b) If user has Facebook account, they can choose Login by Facebook function. SDK syncs Facebook ID from Facebook App to the game.

	c) If user want to use 9Chau account, they can register and login by 9Chau login function. After that, they must create game account or choose account they created before.

##### [3.2. Payment](#header1):
	a) Telco Card Payment: User must enter completely and accurately serial code and pin code when recharging to game.
	b) C Payment: C is money unit in 9Chau system, user can use C for recharging to game.
	c) Google wallet: recharging by google payment service



    
    
    




### [4. Requirements](#header1)
Please make sure development environment and your game meet the following requirements before integrating SDK.

#####[4.1. Development Environment](#header1)
- For Android development Platform Version 4.0 / API Level 14 is the minimum supported platform.
- You also can use any Android development tool for integrating SDK. But using **Android Studio Tool** (1.2.0 is current version) is the best. 



    
    
    


### [5. Important Notes](#header1)

- Only using exact SDK for each game. If using another SDK, we do not guarantee that data returns correctly.
- Please note that the data accumulated during SDK testing is accumulated as actual measurement data.
- Using authentication function by my 9Chau system.
- Using real Telo card for testing payment function.



    
    
    


### [6. How to integrate SDK](#header1)

#### Import 9chauSDK 
1.	On Android Studio, select File menu -> New -> New Module -> Choose Module Type is Phone and Tablet Application, choose More Modules is Import .JAR or .AAR Package and click Next button -> In File Name input field, click browse button and choose SDK .AAR  file we sent. In Subproject name input field, typing **9chauSDK** and click Finish button.
2.	Update your game app build.grandle by adding dependencies at bottom file:

```java
    dependencies {
        compile fileTree(dir: 'libs', include: ['*.jar'])
        compile 'com.android.support:appcompat-v7:22.1.1'
        compile project(':9chauSDK')
        compile 'com.facebook.android:facebook-android-sdk:4.0.0'
        compile 'com.mcxiaoke.volley:library:1.0.+'
    	compile 'com.google.android.gms:play-services:7.5.0'
    }
```

####Config project
Add exact this meta-data into your **AndroidManifest.xml**:

```java
    <meta-data android:name="game_code" android:value="trieu-hoi-3d" />
```

####Add new BroadcastReceiver
An Android application cannot have multiple receivers which have the same intent-filtered action. If you want have more than one **INSTALL_REFFERER** receiver, you must make the proxy receiver like this:

- Add this receiver into your AndroidManifest.xml
    
```java
    <receiver
        android:name="{your_package_name}.tracking.Install"
        android:exported="true" >
        <intent-filter>
            <action android:name="com.android.vending.INSTALL_REFERRER" />
        </intent-filter>
    </receiver>
```
    
- Create package name is **tracking**, then create **Install.java** in this package:
    
```java
    public class Install extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            com.cuuchau.sdk9chau.tracking.InstallationReceiver installationReceiver = new com.cuuchau.sdk9chau.tracking.InstallationReceiver();
            installationReceiver.onReceive(context,intent);

    		// your code here

        }
    }
```

#### Initialize SDK

Add CuuChauSdk.sdk Initialize(this) into onCreate method in your main activity.

**Sample code:**

```java
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// Add this line to your code
		CuuChauSdk.sdkInitialize(this);
	}
```

####Add authentication function

	To show authentication function, add this script to your main activity, in **onCreate** method

**Sample code:**

```java
    CuuChauSdk.showAuthPanel(new AuthCallback() {
        @Override
        protected void onLoginSuccess(JSONObject user) {
        	//your code here
        }
    
        @Override
        protected void onRegisterSuccess(JSONObject user) {
    	    //your code here
        }
    
        @Override
        protected void onLogout() {
        	//your code here
        }
    });
```



#####Methods of AuthCallback:

| Methods | Parameters | Description  |
|:--------|:---------|:-----|
| onLoginSuccess      | JSONObject | Called after user login successfully |
| onRegisterSuccess      | JSONObject      |   Called after user create new game account |
| onLogout |       |  Called when user sign out   |



#####User Properties:
|Properties | Type | Description |
|:---|:---|:---|
|status|String|status = 1 (successful), otherwise is not successful|
|message| String |
|username| String |
|game_code| String |
|token|	String |
|error_code| String	|
|session_key| String |

If you want to get username property, you can access to **user** object by use this script: user.getString("username");


####Add payment function

To show payment function, add this script to payment button click event:

```java
    CuuChauSdk.showRechargePanel(gameOrder, new PaymentCallback() {
        @Override
        public void onSuccess() {
	        // your code here
        }
    });
```

*Note: type of gameOrder parameter is json string.*

**Sample code:**

```java
    @Override
    public void onClick(View v) {
        if(v.getId()== R.id.btnCharge){
            CuuChauSdk.showRechargePanel(gameOrder, new PaymentCallback() {
                @Override
                public void onSuccess() {
    		        // your code here
                }
            });
        }
    }
```

#####PaymentCallback Methods:

|Methods|Parameters|Description|
|:---|:---|:---|
|onSuccess| |Called after recharging success|

####Add profile function
To show profile, please add this script to profile button: CuuChauSdk.showProfilePanel();

**Sample code:**

```java
    @Override
    public void onClick(View v) {
        if(v.getId()== R.id.btnProfile){
            CuuChauSdk.showProfilePanel();
        }
    }
```
