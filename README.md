# 9Chau SDK integration
##### CONNECTION ON SERVER SIDE GUIDE



###Contents
[I. REGISTER](#1-register)

[II. AUTHENTICATION](#2-authentication)

[II. PAYMENT](#1-payment)

### [I. REGISTER](#1-register)

In case user register a new game account (1), when user submit, 9chau SDK send information (username, password) to 9chau server (1.1). If that information is valid, 9chau server send a register request to game server to verify (1.1.1).

***a) Request (1.1.1):***
- Send request: 9CHAU server
- Receive request: PARTNER server
- URL: **http://partner_register_url** 
- Method: **POST**
- Params:
    1. username
    2. game_code
    3. token
- *token rule is md5(9chau_sdk_{game_code}_{username})*
 
After verify data, game server send a response to 9chau server (1.1.2):

***b) Response (1.1.2):***
- Type: **json**
- Format result:

        ```
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

    - **Status** = 1 (successful), otherwise is not successful
    - **Message** is detail of **status**
    - **Username** (duplicate with **username** 9chau server sent)
    - **Game_code** (duplicate with **game_code** 9chau server sent)
    - **Token** (duplicate with **token** 9chau server sent)
    - **Error_code** (created from game server)
    - **Session_key** (create from game server)


### [II. AUTHENTICATION](#2-authentication)
After user submit login information (1), 9chau SDK send user’s information (username, password) to 9chau server (1.1). 9chau server verify and send to game server a request (1.1.1).

***a) Request(1.1.1):***
- Send request: 9CHAU server 
- Receive request: PARTNER server
- URL: http://partner_authen_url 
- Method: POST
- Params:
    1. username
    2. game_code
    3. token
- *token rule is md5(9chau_sdk_{game_code}_{username})*

When received the request, game server proceed received data. If allow user to login game, game server send a response to 9chau server with information as below (1.1.2):

***b) Response (1.1.2):***
- Type: **json**
- Format result: 

    ```
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

    - **Status** = 1 (successful), otherwise is not successful
    - **Message** is detail of **status**
    - **Username** (duplicate with **username** 9chau server sent)
    - **Game_code** (duplicate with **game_code** 9chau server sent)
    - **Token** (duplicate with **token** 9chau server sent)
    - **Error_code** (created from game server)
    - **Session_key** (create from game server)

After receiving the response from partner server, 9chau server will compare data that 9chau server sent and that game server sent. If match, 9chau server response to 9chau SDK and 9chau SDK will allow user to login.





