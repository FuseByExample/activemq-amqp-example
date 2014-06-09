ActiveMQ AMQP Example
=============================

This is an example of using [Apache ActiveMQ]'s [AMQP] 1.0 protocol with the [Apache Qpid] JMS client.

Notes:

* all instructions assume you are executing from the top level directory of this project
* it is assumed you have [Apache Maven] installed, and that you are familiar with its usage
* assumes you are using [Apache ActiveMQ] 5.9.0 or later, or [JBoss A-MQ] 6.1.0 or later

Note: All of this code will run correctly against either [Apache ActiveMQ] 5.9.0 or [JBoss A-MQ] 6.1.0 as both
internally use the same ActiveMQ 5.9.0 code base. The instructions on how to start (command line) the brokers from an
ActiveMQ install will **not** work with [JBoss A-MQ] (e.g. `bin/amq`) as [JBoss A-MQ] has ActiveMQ
deployed within an [Apache Karaf] container to allow for runtime updates to configuration information (versus needing to
restart the broker in the case on [Apache ActiveMQ]). There are additional steps required to use these provided broker
configuration files and deploy them correctly to [JBoss A-MQ] with its Fabric based configuration system.

[JBoss A-MQ]'s distribution contains an `extras` directory with a supported version of the [Apache ActiveMQ] 5.9.0 binary
install.

JBoss A-MQ 6.1.0 Setup
----------------------

For this example to work, it assumes that:

* A user named `admin` with password `admin` has been configured. This can be done by adding `admin=admin,admin` to
  the end of the file `etc/users.properties`
* You have added the AMQP 1.0 transport connector. Add the following to the `transportConnectors` section of the
  `etc/activemq.xml` file (more details here http://activemq.apache.org/amqp.html):

```xml
    <transportConnector name="amqp" uri="amqp://0.0.0.0:5672"/>
```

Starting ActiveMQ
-----------------

Start the broker in a shell:

    shell1> mvn -P broker

Alternatively you can start from an Apache ActiveMQ install:

    shell1> <activemq_home>/bin/activemq console xbean:file:conf/activemq-amqp.xml

Start the message consumer in another shell:

    shell2> mvn -P consumer

Start the message producer in another shell:

    shell3> mvn -P producer

The message producer is coded to send 100 messages. The consumer will log the messages it receives, and will timeout
and exit after 120 seconds of inactivity (no messages received).


[JBoss A-MQ]: https://www.jboss.org/products/amq.html
[Apache ActiveMQ]: http://activemq.apache.org
[Apache Qpid]: http://qpid.apache.org
[Apache Maven]: http://maven.apache.org
[Apache Karaf]: http://karaf.apache.org
[AMQP]: http://www.amqp.org