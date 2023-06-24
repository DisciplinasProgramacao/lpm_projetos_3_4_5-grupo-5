[![Open in Codespaces](https://classroom.github.com/assets/launch-codespace-7f7980b617ed060a017424585567c406b6ee15c891e84e1186181d67ecf80aa0.svg)](https://classroom.github.com/open-in-codespaces?assignment_repo_id=10848517)
# Projeto 3-5
Dentre as formas de entretenimento contemporâneas, os serviços de streaming de conteúdo se multiplicaram e
tomaram conta de boa parte do mercado nos últimos anos, como pode ser percebido em números relatados por
pesquisas recentes.
A companhia de monitoramento de mercado Hibou realizou uma pesquisa em maio de 2022, e seu resultado
aponta que 71% dos brasileiros usam ou já usaram algum serviço de streaming.1 Segundo a mesma pesquisa, 70%
destes usuários têm como principal motivo da assinatura o fato de poder ver séries originais das plataformas, muitas
vezes realizando o que ficou conhecido como maratonar uma série, ou seja, ver todos os seus episódios de maneira
consecutiva em poucos dias.
Recentemente, a Forbes divulgou números espantosos para os serviços de streaming mais conhecidos2: a Netflix
tinha mundialmente, em fevereiro de 2023, mais de 230 milhões de assinantes. Outras empresas apresentam números
igualmente enormes: 161 milhões para a Disney+ e mais de 77 milhões para o grupo Paramount

## Nota base: 12

### App 4/6 (5 pontos) = 3,3
	Protótipo de sistema 4 ✔✔✔ (menu volta sempre pra raiz)
	Robustez do protótipo 2 ✔ (dá a mensagem e... termina)
	
### Requisitos principais 17,5/24 + 6/6 (10 pontos) = 7,8
	Implementação das classes Cliente, Serie, Midia, Filme e PlataformaStreaming 2 ✔✔
	Carga de dados 2 ✔✔
	Cadastro e salvamento 2  ✔
	Audiência da mídia 1 ✔
	Implementação do sistema de avaliação de mídias: uma mídia tem sua avaliação média; 2 ✔✔
	Um cliente não pode avaliar a mesma mídia duas vezes; 1 ✔
	Clientes podem ser especialistas, e estes últimos podem adicionar comentários à avaliação; 3  ✔
	Verificação de especialistas 2  ✔✔
	Os gêneros de mídias devem ser limitados à esta lista 1  ✔
	Algumas mídias serão marcadas como “Lançamento”. 1 ✔
	Estas mídias só poderão ser assistidas por clientes “profissionais” 2 ✔
	Clientes Profissionais também podem escrever comentários para as mídias assistidas. 2 ✔
	Padrão de projeto	3 ✔➗ (state 'falso' pelos instanceof)
	
	Relatórios 6/6   
	
		Qual cliente assistiu mais mídias, e quantas mídias; 
		Qual cliente tem mais avaliações, e quantas avaliações;  
		Qual a porcentagem dos clientes com pelo menos 15 avaliações; 
		Quais são as 10 mídias com a melhor média de avaliações, vistas pelo menos 100 vezes, decrescente; 
		Quais são as 10 mídias com mais visualizações, em ordem decrescente; 
		Estes mesmos dois últimos relatórios, porém com as mídias separadas por gênero. 
	
### Documentação 4,5/7 (5 pontos) = 3,2
	Documentação de código 3 ✔✔➗ (parametros, retornos)
	Diagrama atualizado    2 ✔ (desatualizado)
	Backlog 			   2 ✔ (desatualizado)
	
	
### SOLID - Descontos: -2,3
	sem nenhuma instrução de uso. Passei muito tempo para entender o login de adm
	exceção genérica
	instanceof no audiência !!!
	tipoMidia!!! (polimorfismo)
	ISP: regular recebendo comentário
	
### Apresentação - Peso ou desconto

	

## Alunos integrantes da equipe

* Ana Carolina de Carvalho Corrêa
* Breno Rosa Almeida
* Jordana de Souza Meireles
* Henrique Pinto Santos
* Pedro Talma Toledo

## Professores responsáveis

* João Caram Santos de Oliveira

