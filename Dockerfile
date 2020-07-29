FROM openjdk:8
ADD /target/service-transactions-0.0.1-SNAPSHOT.jar service-transactions.jar 
EXPOSE 8076
CMD ["java","-jar","service-transactions.jar"]  
