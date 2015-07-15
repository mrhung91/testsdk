# 9Chau SDK integration
##### CONNECTION ON SERVER SIDE GUIDE



###Contents

[I. REGISTRATION](#registration)

[II. AUTHENTICATION](#authentication)

[III. PAYMENT](#payment)


### I. REGISTRATION

![Alt text](http://i.imgur.com/Yno6fyO.png "I.1 Register sequence diagram")

I.1 Register sequence diagram


In case user register a new game account (1), when user submit, 9chau SDK send information (username, password) to 9chau server (1.1). If that information is valid, 9chau server send a register request to game server to verify (1.1.1).

***a) Request (1.1.1):***
- Send request: **9CHAU server** 
- Receive request: **PARTNER server**
- URL: **http://partner_register_url**
- Method: **POST**
- Params:
<dl>
<dd>1. username</dd>
<dd>2. game_code</dd>
<dd>3. token</dd>
</dl>
- *token rule is md5(9chau_sdk_{game_code}_{username})*
 
After verify data, game server send a response to 9chau server (1.1.2):

***b) Response (1.1.2):***
- Type: **json**
- Format result:
    ```java
    [{
        "status": "",
        "message": "",
        "username": "",
        "game_code": "",
        "token": "",
        "error_code": "",
        "session_key": "",
    }]
    ```

    - **status** = 1 (successful), otherwise is not successful
    - **message** is detail of status
    - **username** (duplicate with **username** 9chau server sent)
    - **game_code** (duplicate with **game_code** 9chau server sent)
    - **token** (duplicate with **token** 9chau server sent)
    - **error_code** (created from game server)
    - **session_key** (create from game server)


### II. AUTHENTICATION

![Alt text](http://i.imgur.com/6puCuRq.png "II.1 Authentication sequence diagram")

II.1 Authentication sequence diagram

After user submit login information (1), 9chau SDK send user’s information (username, password) to 9chau server (1.1). 9chau server verify and send to game server a request (1.1.1).

***a) Request(1.1.1):***
- Send request: **9CHAU server** 
- Receive request: **PARTNER server**
- URL: **http://partner_authen_url**
- Method: **POST**
- Params:
<dl>
<dd>1. username</dd>
<dd>2. game_code</dd>
<dd>3. token</dd>
</dl>
- *token rule is md5(9chau_sdk_{game_code}_{username})*

When received the request, game server proceed received data. If allow user to login game, game server send a response to 9chau server with information as below (1.1.2):

***b) Response (1.1.2):***
- Type: **json**
- Format result: 

    ```java
    [{
        "status": "",
        "message": "",
        "username": "",
        "game_code": "",
        "token": "",
        "error_code": "",
        "session_key": "",
    }]
    ```

    - **status** = 1 (successful), otherwise is not successful
    - **message** is detail of status
    - **username** (duplicate with **username** 9chau server sent)
    - **game_code** (duplicate with **game_code** 9chau server sent)
    - **token** (duplicate with **token** 9chau server sent)
    - **error_code** (created from game server)
    - **session_key** (create from game server)

After receiving the response from partner server, 9chau server will compare data that 9chau server sent and that game server sent. If match, 9chau server response to 9chau SDK and 9chau SDK will allow user to login.


### III. PAYMENT

![Alt text](http://i.imgur.com/AiROiPl.png "III.1 Payment sequence diagram")

III.1 Payment sequence diagram

When user request to recharge (1), Game application call to 9Chau SDK with game_order parameter (1.1).

After user enter card information (1.1). 9chau SDK send that information to 9chau server (1.1.1). 9chau server verify this card and send a request to game server (1.1.1.1):
***a) Request (1.1.1.1):***
- Send request: **9CHAU server**
- Receive request: **PARTNER server**
- URL: **http://partner_payment_url** 
- Method: **POST**
- Params:
<dl>
<dd>1. **username** (game account)</dd>
<dd>2. **game_code** (unique code of the game)</dd>
<dd>3. **trans_id** ( transaction id )</dd>
<dd>4. **telco** (name of card provider)</dd>
<dd>5. **serial**</dd>
<dd>6. **pincode**</dd>
<dd>7. **money** (Real amount user recharges)</dd>
<dd>8. **currency** (money unit)</dd>
<dd>9. **game_money** (Amount is received in game server. It is the amount to be converted at the rate of VND and KNB)</dd>
<dd>10. **token** (token rule is md5(9chau_sdk_{serial}_{pincode}))</dd>
<dd>11. **game_order** (game order code is sent from game server)</dd>
</dl>

After receiving the request, game server proceed that data and send a response to 9chau server (2.3), details as below:

***b) Response(2.3):***
- Type: **json**
- Format result: 
    ```java
    [{
        "status": "",
        "message": "",
        "username": "",
        "game_code": "",
        "trans_id": "",
        "telco ": "",
        "serial": "",
        "pincode": "",
        "money": "", 
        "token": "", 
        "game_order": "", 
    }]
    ```

    - **status** = 1 (successful), otherwise is not successful
    - **message** is detail of status
    - **token**(duplicate with **token** 9chau server sent)
    - **game_order** (game order code is sent from game server)



