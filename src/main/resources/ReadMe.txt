Step to run JWT:

1. First run http://localhost:9091/token along with username and password as defined in CustomUserDetailsService.
{
    "username": "amir",
    "password": "amir123"
}

2. It will generate token like below
{
    "token": "----"
}

3. Copy the token and then send it in Authorization Header with the API request

Authorization : Bearer token

4. Don't forget to add Bearer with the token.