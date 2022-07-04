# Aiven Kafka Quickstart for Java Developers

Author: https://github.com/whynotkafka

4 Jul 2022

**[Introduction](#_9bgnw3pnue98) 1**

**[Project Overview](#_a48wpqvx0j3m) 1**

[Prerequisites](#_arlbhupwbpzv) 1

[Twitter API](#_8v343x8z06gg) 1

[Implementation](#_fbes1l3ygsm6) 2

## Introduction

This quickstart tutorial will enable developers to integrate their Java applications with Kafka. By the end of this document, you will have an overview of how to produce messages, commit those messages to Kafka and deploy cloud native data infrastructure in no time at all.

## Project Overview

The objective of this project is to retrieve tweets from twitter, commit them to a kafka topic and verify they have landed on the kafka service hosted the cloud data platform aiven.io.

## Prerequisites

Before continuing some familiarity is expected with the following:

- Development environment
    - IDE, for example Intellij
    - Java 8 JDK
    - Git/bitbucket source control
    - Twitter Account
    - io Account
        - [https://console.aiven.io/signup](https://console.aiven.io/signup)

## Twitter API

To retrieve tweets from twitter, please register to access the twitter development portal. You will need credentials to authenticate with the twitter API and in particular an auth bearer token. The auth bearer token is a random string that will prove your identity to twitter and this will need to be exported as a system variable before starting the application.

To learn more about the twitter API:

[https://github.com/twitterdev/twitter-api-java-sdk](https://github.com/twitterdev/twitter-api-java-sdk)

## Implementation

In this section, implementation of the project is documented, this can be used as an example to incorporate in your existing applications.

1. Clone the following git repo

**cd $HOME/my\_development\_dir**

**git clone [https://github.com/whynotkafka/kafka\_demo.git](https://github.com/whynotkafka/kafka_demo.git)**

1. Open Intellji and open the project in the my\_development\_dir/kafka\_demo
- Wait for the Maven dependencies to be retrieved

1. In the left hand side menu, navigate to whynotkafka.demo folder: ![](RackMultipart20220704-1-o1q7o5_html_7b12206a6977cf86.png)

- The following example classes are provided:
    1. JSONProducer - Sends and commits our messages to Kafka in JSON format.
    2. Main - This where the application will be started and all objects in the Java application instantiated.
    3. TweetRetriever - Retrieves data from the Twitter API.

Learn more about our Java examples here:

[https://help.aiven.io/en/articles/5344053-java-examples-for-testing-aiven-for-apache-kafka](https://help.aiven.io/en/articles/5344053-java-examples-for-testing-aiven-for-apache-kafka)

1. For this exercise we will open the Main class.
- Before we continue take note of the Kafka topicName:
- ![](RackMultipart20220704-1-o1q7o5_html_2825c8ff5236226b.png) In the picture above, on line 17, we can see the topicName &quot;tweets&quot;, note this for later, it&#39;s important as this is the topic where the kafka messages will be sent.
2. Open the web browser and head to aiven.io.
3. Provision the following services:
- Kafka
- InfluxDB
- Grafana
4. Setup Integrations between services, so that metrics can been synced from Kafka to InfluxDB and then displayed in Grafana.
- [https://help.aiven.io/en/articles/1456441-getting-started-with-service-integrations](https://help.aiven.io/en/articles/1456441-getting-started-with-service-integrations)

1. Retrieve certificates from Kafka
- [https://developer.aiven.io/docs/products/kafka/howto/keystore-truststore](https://developer.aiven.io/docs/products/kafka/howto/keystore-truststore)

1. Set variables in the project settings. Including:
- TWITTER\_BEARER\_TOKEN
- KAFKA\_BOOTSTRAP\_SERVERS
- KAFKA\_SECURITY\_PROTOCOL
- KAFKA\_SSL\_TRUSTSTORE\_LOCATION
- KAFKA\_SSL\_TRUSTSTORE\_PASSWORD
- KAFKA\_SSL\_KEYSTORE\_TYPE
- KAFKA\_SSL\_KEYSTORE\_LOCATION
- KAFKA\_SSL\_KEYSTORE\_PASSWORD
- KAFKA\_SSL\_KEY\_PASSWORD
  Example here: [https://www.twilio.com/blog/set-up-env-variables-intellij-idea-java](https://www.twilio.com/blog/set-up-env-variables-intellij-idea-java)

1. Head back to Intellij, go to the left hand side menu, right click the Main class and click &quot;Run &#39;Main.main()&#39;&quot;.

1. Providing the message was sent successfully, in the Run tab for intellij you will see the following message.
   **&quot;Message sent successfully&quot;**

2. Go back to aiven.io, we will now verify the messages have landed on the tweets kafka topic. Go to the Kafka service and make sure to enable the REST API: ![](RackMultipart20220704-1-o1q7o5_html_33e504b09f138e9a.png)
3. Now we will check the messages. Go to topics: ![](RackMultipart20220704-1-o1q7o5_html_6b2e10f9f517aa1d.png)
4. Click the &quot;tweets&quot; topic: ![](RackMultipart20220704-1-o1q7o5_html_bc11294b05971688.png)
5. Click &quot;messages&quot;:
   ![](RackMultipart20220704-1-o1q7o5_html_1a6eac260a4ade0.png)
6. Click &quot;Fetch messages&quot;
7. Now we should see messages, remember to decode from base64.
   ![](RackMultipart20220704-1-o1q7o5_html_9cbc4cb0a8e61bc0.png)
8. Finally, head to grafana service, lets now verify the health of the Kafka service.
   Beautiful metrics:
   ![](RackMultipart20220704-1-o1q7o5_html_198c2dacc61dccec.png)