Repositório criado para armazenar os trabalhos da diciplina Programação II
## Informações Importantes
**Discentes:**
- Júlio C. Oliveira
- Sávio Miranda
- Luiz Moreira

**Link para o Slide:**
https://www.canva.com/design/DAGgG2G365I/hnSILpo7AsLpcBLktwrFnA/edit?utm_content=DAGgG2G365I&utm_campaign=designshare&utm_medium=link2&utm_source=sharebutton

## Requisitos do Jogo de Turno
1. Cada héroi e monstro deverá ter, pelo menos os seguintes atributos, (Feito):
	- [x] Nome.
	- [x] Pontos de Vida.
	- [x] Força de Ataque.
	- [x] Defesa.
 	- [x] Destreza. Quando maior, mais chance de acertar um ataque.
 	- [x] Velocidade. Quanto maior, mais chance de desviar de um ataque.
 	- [x] Os atributos devem ser definidos aléatoriamente dentro de um intervalo pré definido.
2. Cada héroi e monstro deverá ter, pelo menos as seguintes implementações, (Feito):
   	- [x] Um construtor.
	- [x] Getters e Setters.
   	- [x] Um método abstrato chamado realizarAtaque(), o método na nossa se chama carryOutAttack, é válido?.
	- [x] Um método estático que gere um héroi ou um monstro, o nosso método não gera especificamente um ou outro ainda.
3. Acomode o jogo dentro de uma classe chamada Game, que deve acomodar os seguintes requisitos: (Feito)
	- [x] Estado do Jogo.
	- [x] N° de Hérois.
	- [x] N° de Monstros.
	- [x] Player com maior/menor atributo específico no campo de batalha.
	- [x] Métodos para iniciar e finalizar o jogo, não entendi como assim finalizar? o jogo vai ser iniciado e vai rodar em segundos, pra quê serve a função de finalizar se o jogo é automático.
	- [x] Controle de fluxo, não entendi o que é isso.
	- [x] Gerenciar os turnos.
	- [x] Armazenar os Logs do Jogo, crie uma classe Log para este requisito.
	- [x] Diferentes níveis de dificuldade, com monstros mais fortes dependendo do nível.
4. Acomode os Turnos dentro de uma classe chamada Turno, que deve atender os seguintes requisitos: (Feito)
	- [x] Realizar os turnos do Jogo.
	- [x] Controlar as ações dos players.
	- [x] Ordenar os players com base em algum atributo.
	- [x] Armazenar o resultado do ataque que será um ENUM.
5. O Enum deve modelar o resultado de um Ataque para os valores (Feito):
	- [x] ERROU.
	- [x] ACERTOU.
	- [x] CRITICAL_HIT.
6. A batalha deve ser simulada automáticamente (Feito):
	- [x] Mecanismos que permitem visualizar o resultado do jogo devem ser implementados.
	- [x] Método pra visualizar as ocorrências ao longo do jogo, a partir dos logs.
7. Implemente uma inteligência artificial naive para os monstros, tais como: (Feito)
	- [x] Atacar o herói com o menor atributo.
		- [x] Menor HP.
		- [x] Menor Defesa.
		- [x] Menor Velocidade.
	- [x] Atacar o mesmo inimigo.
8. Realizar tratamento de exceções sempre que achar necessário. (Feito).
9. Extras:
	- [ ] Adicionar ao ataque um aviso caso o oponente tenha imunidade.
	- [ ] Terminar de parametrizar os movimentos.
	- [ ] Terminar de parametrizar os tipos de pokémons.
	- [ ] Adicionar o cast dos efeitos colaterais dos movimentos dos pokémons.
	- [ ] Colocar um filtro roxo em cima dos Pokémons no slide.
	- [ ] Colocar um fluxograma.
