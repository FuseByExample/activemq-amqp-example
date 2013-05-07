ActiveMQ AMQP Example
=============================

This is an example of using Apache ActiveMQ's amqp protocol with the Apache qpid JMS client.

Notes:

* all instructions assume you are executing from the top level directory of this project
* it is assumed you have Apache Maven installed, and that you are familiar with its usage
* assumes you are using Apache ActiveMQ 5.8.0 or later, or JBoss A-MQ 6.0.0 or later

Note: All of this code will run correctly against either Apache ActiveMQ 5.8.0 or JBoss A-MQ 6.0.0 as both
internally use the same ActiveMQ 5.8.0 code base. The instructions on how to start (command line) the brokers from an
ActiveMQ install will **not** work with JBoss A-MQ (e.g. `bin/amq`) as JBoss A-MQ has ActiveMQ
deployed within an Apache Karaf container to allow for runtime updates to configuration information (versus needing to
restart the broker in the case on Apache ActiveMQ). There are additional steps required to use these provided broker
configuration files and deploy them correctly to JBoss A-MQ with its Fabric based configuration system.
JBoss A-MQ's install contains an `extras` directory with a support version of the Apache ActiveMQ 5.8.0 binary
install.

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