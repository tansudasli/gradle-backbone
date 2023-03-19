# builds container w/ .jar file
# then run it or attach it to run other Main classes!
# java -cp gradle-sandbox-1.0-SNAPSHOT.jar org.core.M..
# ENTRYPOINT: keeps the container running. so, we can attach it
# if we just put, CMD or RUN, without ETRYPOINT, the container works and exits !!
# we can use both ENTRYPOINT and CMD or just ENTRYPOINT !!
# RUN: for the preperation staffs, apt-get etc..
# CMD: for the program of us
# some forms
#CMD ["executable","param1","param2"] (exec form, this is the preferred form)
#CMD command param1 param2 (shell form)
#ENTRYPOINT ["executable", "param1", "param2"]
#ENTRYPOINT command param1 param2
#
FROM eclipse-temurin:19-alpine
COPY ./build/libs/ /tmp
WORKDIR /tmp
ENTRYPOINT /bin/sh
CMD ["java", "-jar", "gradle-sandbox-1.0-SNAPSHOT.jar", "org.core.Main"]