# Guide

## spec list

- log configuration (xml)
- postgresql connection
  - tunneling required
- spring security
  - need to be refactored
- aws s3 signed-url
  - to get signed-url from file key in private s3 bucket.
  - file key is savaed in database
  - sigened-url is actually from presigned-url method
- graphql with spring as an official

## table schema

- user
- role
- user_roles
- target

# tunneling

- bitvise ssh client
  - navigate 'Login' tab
  - SSH connect to the server to be used as tunneling! 
  - navigate 'C2S' tab
  - fill out local host and port, And DBMS host and port

- C2S
  - Listen Interface: 127.0.0.1
  - Port: 15432
  - Destination host: myrds-instance-1.randomstring.ap-northeast-2.rds.amazonaws.com
  - 5432
