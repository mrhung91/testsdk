# 9Chau SDK integration
##### CONNECTION ON SERVER SIDE GUIDE



###Contents

[I. REGISTRATION](#i-registration)

[II. AUTHENTICATION](#ii-authentication)

[III. PAYMENT](#iii-payment)


### I. REGISTRATION

![Alt text](http://i.imgur.com/Yno6fyO.png "I.1 Register sequence diagram")

*I.1 Register sequence diagram*

In case user register a new game account (1), Game application send a registration request to SDK (1.1). After user submit, 9chau SDK send information (username, password) to 9chau server (1.1.1). 9chau server verify information and send a registration request to game server to verify (1.1.1.1).

***a) Request (1.1.1.1):***
- Send request: **9CHAU server (IP: 125.212.202.45)** 
- Receive request: **PARTNER server**
- URL: **https://game_server_registration_url**
- Method: **POST**
- Params:
<dl>
<dd>1. **username** (string): only a-z A-Z 0-9 and "_" character, length min 6 and max 24</dd>
<dd>2. **user_id** (string): only a-z 0-9 character, length is 24</dd>
<dd>3. **game_code** (string): ("{YOUR_GAME_CODE}")</dd>
<dd>4. **token**: token rule is md5(9chau_sdk_{game_code}_{username})</dd>
</dl>

After verify data, game server return registration state to 9chau server (1.1.1.2):

***b) Response (1.1.1.2):***
- Type: **json string**
- Format result:
    ```java
	[{
		"status": "",
		"message": "",
		"user_id": "",
		"username": "",
		"game_code": "",
		"token": "",
		"error_code": "",
		"session_key": "",
	}]
    ```
    - **status** (int) = 1 (successful), otherwise is not successful
    - **message** (string): describes **status** in detail
    - **user_id** (string): must match with **user_id** 9chau server provides
    - **username** (string): must match with **username** 9chau server provides
    - **game_code** (string): must match **game_code** 9chau server provides
    - **token** (string): must match **token** 9chau server provides
    - **error_code** (string): provides by game server
    - **session_key** (string): provides by game server

- Example:
	```java
	[{
		"status": 1,
		"message": "success",
		"user_id": "55823d606803fa5b8a0cd402",
		"username": "cuongphong",
		"game_code": "{YOUR_GAME_CODE}",
		"token": "4jh328dsj21m8782300139ab",
		"error_code": "",
		"session_key": "",
	}]
	```

### II. AUTHENTICATION

![Alt text](http://i.imgur.com/6puCuRq.png "II.1 Authentication sequence diagram")

*II.1 Authentication sequence diagram*

In case user login (1), Game application send an authentication request to SDK (1.1). After user submit login information, 9chau SDK send this information (username, password) to 9chau server (1.1.1). 9chau server verify information and send an authentication request to game server to verify (1.1.1.1).

***a)	Request(1.1.1.1):***
- Send request: **9CHAU server (IP: 125.212.202.45)** 
- Receive request: **PARTNER server**
- URL: **https://game server_authentication_url**
- Method: **POST**
- Params:
<dl>
<dd>1. **username** (string): only a-z A-Z 0-9 and "_" character, length min 6 and max 24</dd>
<dd>2. **user_id** (string): only a-z 0-9 character, length is 24</dd>
<dd>3. **game_code** (string): ("{YOUR_GAME_CODE}")</dd>
<dd>4. **token**: token rule is md5(9chau_sdk_{game_code}_{username})</dd>
</dl>

After verify data, game server return authentication state to 9chau server (1.1.1.2):

***b)	Response (1.1.1.2):***
- Type: **json string**
- Format result: 
    ```java
	[{
		"status": "",
		"message": "",
		"user_id": "",
		"username": "",
		"game_code": "",
		"token": "",
		"error_code": "",
		"session_key": "",
	}]
    ```
    - **status** (int) = 1 (successful), otherwise is not successful
    - **message** (string): describes **status** in detail
    - **user_id** (string): must match with **user_id** 9chau server provides
    - **username** (string): must match with **username** 9chau server provides
    - **game_code** (string): must match **game_code** 9chau server provides
    - **token** (string): must match **token** 9chau server provides
    - **error_code** (string): provides by game server
    - **session_key** (string): provides by game server

- Example:
	```java
	[{
		"status": 1,
		"message": "success",
		"user_id": "55823d606803fa5b8a0cd402",
		"username": "cuongphong",
		"game_code": "{YOUR_GAME_CODE}",
		"token": "4jh328dsj21m8782300139ab",
		"error_code": "",
		"session_key": "",
	}]
	```

### III. PAYMENT

![Alt text](http://i.imgur.com/AiROiPl.png "III.1 Payment sequence diagram")

*III.1 Payment sequence diagram*

When user request to recharge (1), Game application call to 9Chau SDK with game_order parameter (1.1).

After user enter card information, 9chau SDK send this payment information to 9chau server (1.1.1). 9chau server verify this card and send a request to game server (1.1.1.1):

***a) Request (1.1.1.1):***
- Send request: **9CHAU server (IP: 125.212.202.45)**
- Receive request: **PARTNER server**
- URL: **https://game server_payment_url** 
- Method: **POST**
- Params:
<dl>
<dd>1. **username** (string)</dd>
<dd>2. **user_id** (string)</dd>
<dd>3. **game_code** ("{YOUR_GAME_CODE}")</dd>
<dd>4. **trans_id** (string): transaction id</dd>
<dd>5. **telco** (string): name of card provider</dd>
<dd>6. **serial** (string)</dd>
<dd>7. **pincode** (string)</dd>
<dd>8. **money** (int): amount user recharges</dd>
<dd>9. **currency** (string): money unit (VND, USD, …)</dd>
<dd>10. **game_money** (int): amount to be converted at the rate of money currency (VND, USD, …) and game money currency (KNB, XU, GOLD, …)</dd>
<dd>11. **token** (string): token rule is md5(9chau_sdk_{serial}_{pincode})</dd>
<dd>12. **game_order** (string): must match with game_order game server provides</dd>
</dl>

After receiving the request, game server proceed that data and send a response to 9chau server (2.3), details as below:

***b) Response(2.3):***
- Type: **json string**
- Format result: 
    ```java
	[{
		"status": "",
		"message": "",
		"username": "",
		"user_id": "",
		"game_code": "",
		"trans_id": "",
		"telco ": "",
		"serial": "",
		"pincode": "",
		"money": "", 
		"currency": "", 
		"game_money": "", 
		"token": "", 
		"game_order": "", 
	}]
    ```
	-	**status** (int) = 1 is successful, otherwise is not successful
	-	**message** (string): describes **status** in detail
	-	**username** (string): must match **username** 9chau server provides
	-	**user_id** (string): must match **user_id** 9chau server provides
	-	**game_code** ("{YOUR_GAME_CODE}")
	-	**trans_id** (string): must match **trans_id** 9chau server provides
	-	**telco** (string): must match **telco** 9chau server provides
	-	**serial** (string): must match **serial** 9chau server provides
	-	**pincode** (string): must match **pincode** 9chau server provides
	-	**money** (int): must match **money** 9chau server provides
	-	**currency** (string): must match **currency** 9chau server provides
	-	**game_money** (int): must match **game_money** 9chau server provides
	-	**token** (string): must match **token** 9chau server provides
	-	**game_order** (string): must match with **game_order** 9chau server provides

- Example:
	```java
	[{
		"status": 1,
		"message": "success",
		"username": "cuongphong",
		"user_id": "55823d606803fa5b8a0cd402",
		"game_code": "{YOUR_GAME_CODE}",
		"trans_id": "558215e66803fa578a0cd405",
		"telco ": "VTE",
		"serial": "1234567891234",
		"pincode": "98723123456273645",
		"money": 100000, 
		"currency": "VND", 
		"game_money": 150, 
		"token": "3jh8dshywiwsh23sd7882jdsdyhsjea9b", 
		"game_order": "23431123245", 
	}]
	```
