# Contas a Pagar - API

Este é um projeto de API para gerenciar contas a pagar. A aplicação foi construída com Spring Boot e utiliza um banco de dados PostgreSQL. O projeto inclui endpoints RESTful para criar, atualizar, deletar e visualizar contas.

Micael Santana - qualquer duvida contato micaelparadox@gmail.com

## Como rodar o projeto

### 1. Instalar o Docker

#### Se você estiver usando **Windows** com WSL 2

Para configurar o Docker no Windows com WSL 2, siga o guia abaixo:
- [Guia de instalação do Docker no WSL 2 com Ubuntu 22.04](https://medium.com/@habbema/guia-de-instala%C3%A7%C3%A3o-do-docker-no-wsl-2-com-ubuntu-22-04-9ceabe4d79e8)

#### Se você estiver usando **Linux**

Se estiver utilizando Linux, você pode seguir o guia de instalação ou atualização do Docker e Docker Compose a partir do link abaixo:
- [Como instalar ou atualizar o Docker e Docker Compose no Linux](https://simplescloud.io/como-instalar-ou-atualizar-o-docker-e-o-docker-compose)

### 2. Clonar o repositório

Primeiro, clone este repositório para sua máquina local:

```bash
git clone https://github.com/seu-usuario/contasapagar.git
cd contasapagar
```

### 3. Construir as imagens do Docker

Para construir as imagens Docker do projeto, utilize o comando:

```bash
docker compose build --no-cache
```

### 4. Subir o projeto

Após construir as imagens, rode o comando abaixo para iniciar a aplicação e o banco de dados:

```bash
docker compose up --build
```

### 5. Testar a API

A API estará disponível na seguinte URL:

```
http://localhost:8080/api/v1/contas
```

**Atenção**: Para realizar qualquer requisição, você deve utilizar autenticação básica (**Basic Auth**) com as seguintes credenciais:

- **Username**: `admin`
- **Password**: `admin`

### Endpoints principais

- **POST** `/api/v1/contas` – Criação de uma nova conta
- **GET** `/api/v1/contas` – Listar todas as contas
- **PUT** `/api/v1/contas/{id}` – Atualizar uma conta existente
- **PATCH** `/api/v1/contas/{id}/situacao` – Atualizar a situação de uma conta
- **GET** `/api/v1/contas/{id}` – Buscar uma conta por ID
- **GET** `/api/v1/contas/total-pago` – Obter o total pago em um período

### 6. Encerrar o projeto

Para encerrar os containers e parar a aplicação, use:

```bash
docker compose down
```

---

## Como usar no Postman

### 1. Importar um arquivo CSV

- Endpoint: **POST** `/api/v1/import/csv`
- Enviar um arquivo CSV com os seguintes campos: `data_vencimento,data_pagamento,valor,descricao,situacao`.

### Exemplo de arquivo CSV:

```csv
data_vencimento,data_pagamento,valor,descricao,situacao
2023-10-01,2023-10-05,1000.00,Aluguel,Pago
2023-10-15,2023-10-20,500.00,Internet,Pendente
```

### Como configurar no Postman:

1. Selecione o método **POST**.
2. Coloque a URL: `http://localhost:8080/api/v1/import/csv`.
3. Vá até a aba **Authorization**, selecione **Basic Auth** e preencha com:
   - **Username**: `admin`
   - **Password**: `admin`
4. No **Body**, selecione **form-data**.
5. No campo **Key**, escolha a opção **File** e insira um arquivo CSV.
6. No campo **Value**, adicione o arquivo CSV a ser importado.


### Exemplo de resposta:

```json
[
    {
        "id": 1,
        "dataVencimento": "2023-10-01",
        "dataPagamento": "2023-10-05",
        "valor": 1000.0,
        "descricao": "Aluguel",
        "situacao": "Pago",
        "links": [
            {
                "rel": "self",
                "href": "http://localhost:8080/api/v1/contas/1"
            }
        ]
    },
    {
        "id": 2,
        "dataVencimento": "2023-11-15",
        "dataPagamento": "2023-11-16",
        "valor": 500.5,
        "descricao": "Conta de Luz",
        "situacao": "Pago",
        "links": [
            {
                "rel": "self",
                "href": "http://localhost:8080/api/v1/contas/2"
            }
        ]
    },
    {
        "id": 3,
        "dataVencimento": "2023-12-20",
        "dataPagamento": "2023-12-21",
        "valor": 750.75,
        "descricao": "Internet",
        "situacao": "Pendente",
        "links": [
            {
                "rel": "self",
                "href": "http://localhost:8080/api/v1/contas/3"
            }
        ]
    }
]
```