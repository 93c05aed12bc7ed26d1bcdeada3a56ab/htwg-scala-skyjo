FROM hseeberger/scala-sbt
WORKDIR /skyjo
ADD . /skyjo
CMD sbt run