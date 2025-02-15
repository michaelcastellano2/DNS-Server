# DNS Server
This project builds a caching DNS server which runs on local machine. 
Author: Michael Castellano (Dec 2024)

# How to run
First, make sure you are in the repository directory, and run
```bash
make
```
To build the application. 

Then, In one terminal, run:
```bash
sudo java dns.DNSServer local.zone
```

In another terminal, run the dig command. For example:
```bash
dig @localhost test1.hello.net
```

You may also run the other files in the zone, or
another website outside our zone. For example:
```bash
dig @localhost www.google.com
```

You will the server forward the query to external zones and 
eventually return with an answer.

