(ns frontend.core
  (:require [clj-http.client :as client]
            [cheshire.core :as json])
  (:gen-class))


(defn menuteste []
(println "\nOpa! Bem-vindo a nossa calculadora hehe")
(println "=========================================")
(println "1. Cadastrar/Consultar dados pessoais")
(println "2. Registrar consumo de alimento")
(println "3. Registrar realização de atividade")
(println "4. Consultar extrato de transações")
(println "5. Consultar saldo de calorias")
(println "6. Sair")
(flush)


(let [opcaoEscolhida (read-line)]
(cond 


;; primeira opcao onde a gente vai fazer o cadastro ai seguinte dentro do let a gente ta armazenando as variaveis nome idade sexo peso altura
;; onde tem _ é pra salvar variaveis que nao vao ser utilizadas!!! 
(= opcaoEscolhida "1") 
  (do 
    (println "\n--- 1. Cadastrar/Consultar dados pessoais ---")
    (println "A. Cadastrar novos dados")
    (println "B. Consultar dados atuais")
    (print "Escolha (A ou B): ")
    (flush)
    
    (let [subOpcao (read-line)]
      (cond 
        (or (= subOpcao "A") (= subOpcao "a"))
        (do
          (println "\nBem Vindo ao cadastro de dados pessoais")
          
          (let [
          _(println "Digite seu nome:")
          _(flush)
          nome (read-line)

          _(println "Digite sua idade:")
          _(flush)
          idade (read-line)

          _(println "Informe seu sexo (Masculino/Feminino):")
          _(flush)
          sexo (read-line)

          _(println "Informe seu peso:")
          _(flush)
          peso (read-line)

          _(println "Informe sua altura:")
          _(flush)
          altura (read-line)
          ] ;; aqui fecha a chave do let

            (println "Dados pessoais cadastrados com sucesso! Obrigado" nome)
            (println "Aperte ENTER para voltar ao menu inicial")
            (flush)
            (read-line)
            (recur))) ;; parenteses do let e cond

        (or (= subOpcao "B") (= subOpcao "b"))
        (do
          (println "\n Buscando dados no servidorrr")
          (println "get pro back")
          (println "Aperte ENTER para voltar ao menu inicial")
          (flush)
          (read-line)
          (recur))

        :else
        (do
          (println "\nOpção inválida! Voltando ao menu principal...")
          (recur)))))


;; aqui a gente vai registrar o consumo de aljmentos
(= opcaoEscolhida "2")
  (do 
    (println "\nBem-vindo ao registro de consumo de alimentos")

    (let [
      _(println "Qual alimento você consumiu?")
      _(flush)
      alimento (read-line)

      _(println "Qual a data do consumo")
      _(flush)
      data (read-line)

      _(println "Quantas calorias tem esse alimento?")
      _(flush)
      calorias (read-line)

      typpe "ganho"
    ] ;; aqui fecha a chave do let

      (println "Alimento registrado com sucesso! Obrigado" alimento data calorias)
      (println "Aperte ENTER para voltar ao menu inicial")
      (flush)
      (read-line)
      (recur)))


(= opcaoEscolhida "3")
  (do 
    (println "\n--- Registro de Atividade Física ---")
    (let [
      _(println "Qual a atividade física que realizou?")
      _(flush)
      atividade (read-line)

      _(println "Qual a data da atividade?")
      _(flush)
      data (read-line)

      _(println "Qual foi a duração (em minutos)?")
      _(flush)
      duracao (read-line)
      
      typpe "perda"
    ]
      (println "\nAtividade registrada com sucesso no front-end!")
      (println "Aperte ENTER para voltar ao menu inicial")
      (flush)
      (read-line)
      (recur)))


(= opcaoEscolhida "4")
  (do 
    (println "\n--- Extrato de Transações ---")
    (println "back")
    (println "Aperte ENTER para voltar ao menu inicial")
    (flush)
    (read-line)
    (recur))


(= opcaoEscolhida "5")
  (do 
    (println "\n--- Saldo de Calorias ---")
    (println "back")
    (println "Aperte ENTER para voltar ao menu inicial")
    (flush)
    (read-line)
    (recur))


(= opcaoEscolhida "6")
  (println "\nsaindoo...") 

:else
  (do
    (println "tente novamente!!!")
    (recur)))))


(defn -main
  "hehe"
  [& args]
  (menuteste)
  )