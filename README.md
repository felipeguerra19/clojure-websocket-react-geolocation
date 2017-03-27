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

* [JDK 8+] (http://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html) 
* [Leiningen] (https://leiningen.org/)

A sugestão de IDE é o **counterclockwise-0.35.0**, mas sinta-se  à vontade para utilizar outras!

