Step to run JWT:

1. First run http://localhost:9091/token along with username and password as defined in CustomUserDetailsService.
{
    "username": "amir",
    "password": "amir123"
}

2. It will generate token like below
{
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbWlyIiwiZXhwIjoxNjk0Mzg0Mzc3LCJpYXQiOjE2OTQzNjYzNzd9.eokGSIVqvkiyQSczz8A_3DNicT5ODT-sApKrm5Ck8Da9QPKNLsXfeTCsDxqWvsdYLkaNSxlVVClcniLqa8gKGw"
}

3. Copy the token and then send it in Authorization Header with the API request

Authorization : Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbWlyIiwiZXhwIjoxNjk0Mzg0Mzc3LCJpYXQiOjE2OTQzNjYzNzd9.eokGSIVqvkiyQSczz8A_3DNicT5ODT-sApKrm5Ck8Da9QPKNLsXfeTCsDxqWvsdYLkaNSxlVVClcniLqa8gKGw

4. Don't forget to add Bearer with the token.