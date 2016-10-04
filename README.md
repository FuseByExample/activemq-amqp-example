ActiveMQ AMQP Example
=============================

This is an example of using [Apache ActiveMQ]'s [AMQP] 1.0 protocol with the [Apache Qpid] JMS client.

Notes:

* all instructions assume you are executing from the top level directory of this project
* it is assumed you have [Apache Maven] 3.2.3 or newer installed, and that you are familiar with its usage
* assumes you are using [Apache ActiveMQ] 5.11.0 or later, or [JBoss A-MQ] 6.3.0 or later

Note: All of this code will run correctly against either [Apache ActiveMQ] 5.11.0 or [JBoss A-MQ] 6.3.0 as both
internally use the same ActiveMQ 5.11.0 code base. 

JBoss A-MQ 6.3.0 Setup
----------------------

For this example to work, make the following changes to your Apache ActiveMQ or JBoss A-MQ installation

* Create a user named `admin` with password `admin` by adding `admin=admin,admin` to the end of the file `etc/users.properties`
* Add the AMQP 1.0 transport connector bu adding the following to the `transportConnectors` section of the
  `etc/activemq.xml` file (more details here http://activemq.apache.org/amqp.html):

```xml
    <transportConnector name="amqp" uri="amqp://0.0.0.0:5672"/>
```

Starting ActiveMQ
-----------------

Start the broker in a shell from the Apache ActiveMQ install:

    shell1> <activemq_home>/bin/amq 

Start the message consumer in another shell from the top level directory of this project:

    shell2> mvn -P consumer

Start the message producer in another shell from the top level directory of this project:

    shell3> mvn -P producer

The message producer is coded to send 100 messages. The consumer will log the messages it receives, and will timeout
and exit after 120 seconds of inactivity (no messages received).


[JBoss A-MQ]: https://www.jboss.org/products/amq.html
[Apache ActiveMQ]: http://activemq.apache.org
[Apache Qpid]: http://qpid.apache.org
[Apache Maven]: http://maven.apache.org
[Apache Karaf]: http://karaf.apache.org
[AMQP]: http://www.amqp.org