(ns frontend.core
  (:require [clj-http.client :as client]
            [cheshire.core :as json]
            [clojure.string :as str])
  (:gen-class))


(defn menuteste []
  (println "\nOpa! Bem-vindo a nossa calculadora de caloriass")
  (println "=========================================")
  (println "1. Cadastrar/Consultar dados pessoais")
  (println "2. Registrar consumo de alimento")
  (println "3. Registrar realização de atividade")
  (println "4. Consultar extrato de transações")
  (println "5. Consultar saldo de calorias")
  (println "6. Limpar o extrato")
  (println "7. Sair")
  (flush)


  (let [opcaoEscolhida (read-line)]
    (cond


      (= opcaoEscolhida "1")
      (do
        (println "\n1. Cadastrar/Consultar dados pessoais")
        (println "A. Cadastrar novos dados")
        (println "B. Consultar dados atuais")
        (print "Escolha (A ou B): ")
        (flush)

        (let [subOpcao (read-line)]
          (cond
            (or (= subOpcao "A") (= subOpcao "a"))
            (do
              (println "\nCadastro de dados pessoais")

              (let [_ (println "Digite seu nome:")
                    _ (flush)
                    nome (read-line)

                    _ (println "Digite sua idade:")
                    _ (flush)
                    idade (Integer/parseInt (read-line))

                    _ (println "Informe seu sexo (M/F):")
                    _ (flush)
                    sexo (str/upper-case (read-line))

                    _ (println "Informe seu peso (kg):")
                    _ (flush)
                    peso (Double/parseDouble (read-line))

                    _ (println "Informe sua altura (m):")
                    _ (flush)
                    altura (Double/parseDouble (read-line))

                    pacote-json (json/generate-string {:nome nome :idade idade :sexo sexo :peso peso :altura altura})

                    resposta (client/post "http://localhost:3000/usuario"
                                          {:body pacote-json
                                           :content-type :json
                                           :accept :json})

                    dados (json/parse-string (:body resposta) true)] ;; aqui fecha a chave do let

                (println "Dados pessoais cadastrados com sucesso! Obrigado" (:nome dados))
                (println "Aperte ENTER para voltar ao menu inicial")
                (flush)
                (read-line)
                (recur))) ;; parenteses do let e cond

            (or (= subOpcao "B") (= subOpcao "b"))
            (do
              (println "\nBuscando dados no servidor...")

              (let [resposta (client/get "http://localhost:3000/usuario" {:throw-exceptions false})
                    dados (json/parse-string (:body resposta) true)]
                (if (= (:status resposta) 200)
                   (do
                      (println "Nome   :" (:nome dados))
                      (println "Idade  :" (:idade dados))
                      (println "Sexo   :" (:sexo dados))
                      (println "Peso   :" (:peso dados) "kg")
                      (println "Altura :" (:altura dados) "m"))
                   (println "Nenhum usuário cadastrado ainda!")))

              (println "Aperte ENTER para voltar ao menu inicial")
              (flush)
              (read-line)
              (recur))

            :else
            (do
              (println "\nOpção inválida! Voltando ao menu principal...")
              (recur)))))


      (= opcaoEscolhida "2")
      (do
        (println "\nRegistro de consumo de alimentos")

        (let [_ (println "Qual alimento você consumiu?")
              _ (flush)
              alimento (read-line)

              _ (println "Qual a data do consumo? (AAAA-MM-DD)")
              _ (flush)
              data (read-line)

              _ (println "Qual a quantidade consumida (em gramas)?")
              _ (flush)
              quantidade (Double/parseDouble (read-line))

              pacote-json (json/generate-string {:nome alimento :data data :quantidade quantidade})

              resposta (client/post "http://localhost:3000/alimento"
                                    {:body pacote-json
                                     :content-type :json
                                     :accept :json})

              dados (json/parse-string (:body resposta) true)] ;; aqui fecha a chave do let

          (println "Alimento registrado com sucesso!")
          (println "Calorias calculadas pelo servidor:" (:calorias dados) "kcal")
          (println "Aperte ENTER para voltar ao menu inicial")
          (flush)
          (read-line)
          (recur)))


      (= opcaoEscolhida "3")
      (do
        (println "\nRegistro de Atividade Física")
        (let [_ (println "Qual a atividade física que realizou?")
              _ (flush)
              atividade (read-line)

              _ (println "Qual a data da atividade? (AAAA-MM-DD)")
              _ (flush)
              data (read-line)

              _ (println "Qual foi a duração (em minutos)?")
              _ (flush)
              duracao (Double/parseDouble (read-line))

              pacote-json (json/generate-string {:nome atividade :data data :duracao duracao})

              resposta (client/post "http://localhost:3000/atividade"
                                    {:body pacote-json
                                     :content-type :json
                                     :accept :json})

              dados (json/parse-string (:body resposta) true)]
          (println "\nAtividade registrada com sucesso!")
          (println "Calorias gastas calculadas pelo servidor:" (:calorias dados) "kcal")
          (println "Aperte ENTER para voltar ao menu inicial")
          (flush)
          (read-line)
          (recur)))


      (= opcaoEscolhida "4")
      (do
        (println "\nExtrato de Transações")
        (let [resposta (client/get "http://localhost:3000/extrato")
              dados (json/parse-string (:body resposta) true)]
          (println "\nSeu extrato atualizado direto do servidor:")
          (run! (fn [t]
                 (println " -" (:tipo t) "|" (:nome t) "|" (:data t) "|" (:calorias t) "kcal"))
               (:transacoes dados)))
        (println "\nAperte ENTER para voltar ao menu inicial")
        (flush)
        (read-line)
        (recur))


      (= opcaoEscolhida "5")
      (do
        (println "\nSaldo de Calorias")
        (let [resposta (client/get "http://localhost:3000/saldo")
              dados (json/parse-string (:body resposta) true)]
          (println "\nSaldo de calorias:" (:saldo dados) "kcal"))
        (println "\nAperte ENTER para voltar ao menu inicial")
        (flush)
        (read-line)
        (recur))

      (= opcaoEscolhida "6")
      (do
        (println "\nLimpando extrato...")
        (client/delete "http://localhost:3000/limpar")
        (println "Extrato limpo com sucesso!")
        (println "Aperte ENTER para voltar ao menu inicial")
        (flush)
        (read-line)
        (recur))

      (= opcaoEscolhida "7")
      (println "\nsaindoo...")

      :else
      (do
        (println "tente novamente!!!")
        (recur)))))


(defn -main
  "hehe"
  [& args]
  (menuteste))