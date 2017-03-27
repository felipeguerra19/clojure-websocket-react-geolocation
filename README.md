# clojure-websocket-react-geolocation
Esse projeto tem por objetivo demonstrar como construir um *microservice* utilizando **Clojure**
como a linguagem de programação que permite o desenvolvimento baseado no paradigma funcional.

Os dois recursos, inicialmente disponíveis, realizam o cálculo da distância de duas coordenadas geográficas
utilizando a [Fórmula de Haversine](https://pt.wikipedia.org/wiki/F%C3%B3rmula_de_Haversine). 

A idéia foi demonstrar como utilizar o protocolo *Websocket* e o HTTP padrão.

## Motivação ##
O paradigma funcional é algo desafiador para os programadores oriundos de outras técnicas de programção.
Ele trás a necessidade do estudante de pensar por outra prisma, tornando as funcões cidadãs de primeira classe.

Clojure é um dialeto de LISP, então, programadores que já conhecem outras linguagens como [Haskell](https://www.haskell.org/) e
[Erlang](https://www.erlang.org/) vão se sentir mais confortáveis.

Para programadores com larga experiência no mundo Java, o fato de Clojure rodar na JVM, pode ser um fator a mais de atração.

Também, a possibilidade de se construir *microservices* que necessitam de alta performance aliada com baixos investimentos em infra.
Com a Imutabilidade de Clojure isso se torna factível. 

## Setup ##

É necessário os seguintes pré-requisitos para realizar o build & deploy da aplicação:

* [JDK 8+](http://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html) 
* [Leiningen](https://leiningen.org/)

A sugestão de IDE é o **counterclockwise-0.35.0**, mas sinta-se  à vontade para utilizar outras!

### Build ####

Uma vez que o *clone* do projeto foi feito em sua máquina, com o devido *setup* realizado, navegue até a raíz do projeto e execute:
```
lein repl

nREPL server started on port 51716 on host 127.0.0.1 - nrepl://127.0.0.1:51716
REPL-y 0.3.7, nREPL 0.2.12
Clojure 1.6.0
Java HotSpot(TM) 64-Bit Server VM 1.8.0_121-b13
    Docs: (doc function-name-here)
          (find-doc "part-of-name-here")
  Source: (source function-name-here)
 Javadoc: (javadoc java-object-or-class-here)
    Exit: Control+D or (exit) or (quit)
 Results: Stored in vars *1, *2, *3, an exception in *e

br.com.reactive-poc.rest-service=>
```
O projeto está pronto para ser executado:

```
lein run

...

14:06:13.195 INFO  [org.xnio] (main) XNIO version 3.3.6.Final
14:06:13.211 INFO  [org.xnio.nio] (main) XNIO NIO Implementation Version 3.3.6.Final
14:06:13.378 INFO  [org.projectodd.wunderboss.web.Web] (main) Registered web context /geolocation/async
14:06:13.384 INFO  [org.projectodd.wunderboss.web.Web] (main) Registered web context /geolocation
```

Para gerar o **.jar** do projeto basta executar o comando abaixo:
```
lein uberjar
...
Compiling br.com.reactive-poc.rest-service
Created /app/src/target/clojure-websocket-react-geolocation-1.0.0-SNAPSHOT.jar
Created /app/src/target/clojure-websocket-react-geolocation-1.0.0-SNAPSHOT-standalone.jar
```

Verifique se o **.jar** criado está funcional:
```
java -jar target/clojure-websocket-react-geolocation-1.0.0-SNAPSHOT-standalone.jar host 0.0.0.0 port 8001
...
14:13:47.171 INFO  [org.xnio] (main) XNIO version 3.3.6.Final
14:13:47.199 INFO  [org.xnio.nio] (main) XNIO NIO Implementation Version 3.3.6.Final
14:13:47.490 INFO  [org.projectodd.wunderboss.web.Web] (main) Registered web context /geolocation/async
14:13:47.494 INFO  [org.projectodd.wunderboss.web.Web] (main) Registered web context /geolocation
```

Utilizando o cURL/WGET, realize um teste do *end-point*:
```
wget "http://localhost:8001/geolocation/calculate-distance/-23.521398/-46.691071/-23.529398/-46.692071"


--2017-03-27 14:16:19--  http://localhost:8001/geolocation/calculate-distance/-23.521398/-46.691071/-23.529398/-46.692071
Resolving localhost (localhost)... 127.0.0.1
Connecting to localhost (localhost)|127.0.0.1|:8001... connected.
HTTP request sent, awaiting response... 200 OK
Length: 17
Saving to: '-46.692071'
```

## DevOps ##

Para acelerar o processo de estudo desse exemplo, disponibilizo um arquivo [Vagrantfile](Vagrantfile) com os recursos necessários para rodar o projeto em uma máquina virtual.


