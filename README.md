# projeto-poo

Realizar ajustes propostos em aula

1 - Pesquisa por ID e Nome
2 - Melhorar visualização do produtoXfornecedor
3 - Ajustar o deletar em cascade (tornar fornecedor de todos os produtos nulo)
4 - Rever o código para buscar melhorias

{
Realizado por Leo (22/05):

Pesquisa por ID, Nome, Descrição, Login e E-mail (variando o tipo)

Ajuste do deletar em cascade (Coloquei pra quando existe algum item vinculado ao fornecedor que vai ser excluido, o código impossibilitar isso (coloquei uma exceção no controller)

Adicionada a opção de visualizar produto por fornecedor na sub de produtos
}

{
Realizado por Maithe (25/05):

Tela Cliente (1 - Produtos, 2 - Carrinho de Compras, 3 - Pedidos, 4 - Dados do Cliente, 0 - Sair)

Início do SubProduct
-> Lista os produtos (nome e preço) da busca, criando uma nova lista com esses produtos
-> Seleção pelo ID (posição) para ver os detalhes
-> Mostra os detalhes do produto escolhido
-> Se selecionada a opção "Adicionar ao carrinho", pede quantas unidades
-> Se o estoque for menor que o valor selecionado, mostrar a quantidade máxima em estoque e oferecer ao usuário
}

Próximos passos:

SubProduct

//Adicionar código do produto na lista
//Permitir a seleção para ver detalhes pelo código também

// Adicionar ao carrinho //


--------------------------------------------------------
Sub Carrinho de Compras

R${VALOR ATUAL DO CARRINHO}

//Listar todos os produtos do carrinho, com quantidade x preço unitario = preço total do produto //

1. Editar
// Ter a opção de aumentar e dimuir a quantidade de produtos, sempre verificando o estoque //
// Excluir produto do carrinho de compras //

3. Comprar
// Vai verificar todos os estoques novamente, e caso algum nao condiza com a quantidade atual de produtos, ele vai alertar ao usuário e oferecer a quantidade máxima em estoque//

//Adicionar 17% de ICMS//
// Mostrar valor bruto x valor total x taxa icms //

5. Excluir Carrinho
// Limpar tudo do carrinho de compras //


Sub Pedidos
1. Mostrar Pedidos
   // TODOS (últimos 90 dias), CANCELADOS, NOVOS, ENTREGUES //
   
// Estado, Valor
1. Cancelar (Se o pedido for novo)


Sub Dados Clients

EDIT QUE JÁ TEM NA NOSSA 
