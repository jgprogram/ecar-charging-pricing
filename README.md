# eCar charging pricing

Backoffice supporting electric vehicles charging network.

## How to run

App requires JRE 8

 1. Build app using gradle wrapper.
 2. Execute command `java -Dspring.profiles.active=in-memory -jar build/libs/pricing-0.0.1-SNAPSHOT.jar`
 
## Usage examples

This example implements default price policy with rules below and 10% discount policy for VIP customer.

 - 00:00 - 11:59 | 0.05 EUR per min
 - 12:00 - 23:59 | 0.06 EUR per min

For VIP customer use `customerId=VIP-ID`

`http://localhost:8080/pricing?customerId=VIP-ID&startCharging=2018-01-01T00:00&stopCharging=2018-01-01T11:59`

For other customers You can use any non empty string as customer id.

`http://localhost:8080/pricing?customerId=OTHER-ID&startCharging=2018-01-01T00:00&stopCharging=2018-01-01T11:59`

## Schema

![Image of aggregates_schema](https://jgprogram.files.wordpress.com/2018/11/vattenfall_ecar.png)

## Author
 - Jacek Gzel (JGProgram)
