FROM hseeberger/scala-sbt:graalvm-ce-19.3.0-java11_1.3.7_2.12.10
WORKDIR /skyjo
ADD . /skyjo
CMD sbt run

