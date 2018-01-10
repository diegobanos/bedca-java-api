# BEDCA API

## Disclosure

This is a basic API that connects to the BEDCA Database [BEDCA Database](http://www.bedca.net/bdpub/index.php)

It is inspired by a PHP version of this API [PHP API by statickidz](https://github.com/statickidz/bedca-api)

## How to use

There are three basic functions: getFoodGroups, getFoodsInGroup and getFood

    Client client = new Client;
    
    String xmlResponse = client.getFoodGroups();
    
    System.out.print(xmlResponse);
