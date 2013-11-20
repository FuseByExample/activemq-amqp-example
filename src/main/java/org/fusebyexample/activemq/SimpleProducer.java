/*
 * Copyright (C) Red Hat, Inc.
 * http://www.redhat.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.fusebyexample.activemq;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.qpid.amqp_1_0.jms.impl.ConnectionFactoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleProducer {
    private static final Logger LOG = LoggerFactory.getLogger(SimpleProducer.class);

    private static final Boolean NON_TRANSACTED = false;
    private static final int MESSAGE_DELAY_MILLISECONDS = 100;
    private static final int NUM_MESSAGES_TO_BE_SENT = 100;
    public static final String DESTINATION_NAME = "topic://test.topic.simple";

    public static void main(String args[]) {
        Connection connection = null;

        try {
            ConnectionFactory connectionFactory = (ConnectionFactory) new ConnectionFactoryImpl("localhost", 5672, "admin", "admin");
            connection = connectionFactory.createConnection(/*"admin", "admin"*/);
            connection.start();

            Session session = connection.createSession(NON_TRANSACTED, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createTopic(DESTINATION_NAME);
            MessageProducer producer = session.createProducer(destination);

            for (int i = 1; i <= NUM_MESSAGES_TO_BE_SENT; i++) {
                TextMessage message = session.createTextMessage("Message " + i);
                LOG.info("Sending to destination: [" + DESTINATION_NAME + "] this text: '" + message.getText());
                producer.send(message);
                Thread.sleep(MESSAGE_DELAY_MILLISECONDS);
            }

            // Cleanup
            producer.close();
            session.close();
        } catch (Throwable t) {
            LOG.error("Error sending message", t);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    LOG.error("Error closing connection", e);
                }
            }
        }
    }
}
