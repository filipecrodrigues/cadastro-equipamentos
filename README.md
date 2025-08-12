# 📋 Sistema de Cadastro de Equipamentos

Sistema web para gerenciamento de equipamentos de TI desenvolvido com Spring Boot, Thymeleaf e banco H2.

## 🚀 Tecnologias Utilizadas

- **Java 21** - Linguagem de programação
- **Spring Boot 3.3.4** - Framework principal
- **Spring Web MVC** - Camada web
- **Spring Data JPA** - Persistência de dados
- **Thymeleaf** - Template engine
- **H2 Database** - Banco de dados em memória
- **Apache POI** - Exportação de relatórios Excel
- **Maven** - Gerenciamento de dependências
- **Bootstrap CSS** - Interface responsiva

## 📁 Estrutura do Projeto

```
cadastro/
├── src/main/java/cadastro/equipamentos/cadastro/
│   ├── CadastroApplication.java              # Classe principal
│   ├── controllers/
│   │   ├── EquipamentoController.java        # Controller principal
│   │   └── HomeController.java               # Controller de navegação
│   ├── model/
│   │   └── Equipamento.java                  # Entidade JPA
│   ├── repository/
│   │   └── EquipamentoRepository.java        # Interface JPA Repository
│   ├── service/
│   │   └── EquipamentoService.java           # Lógica de negócio
│   └── util/
│       └── ExcelExporter.java                # Utilitário para exportação
├── src/main/resources/
│   ├── templates/                            # Templates Thymeleaf
│   │   ├── equipamentos_list.html
│   │   ├── equipamento_form.html
│   │   └── equipamento_detalhes.html
│   └── application.properties                # Configurações
└── pom.xml                                   # Dependências Maven
```

## ⚙️ Funcionalidades

### 📊 Gestão de Equipamentos
- ✅ **Cadastrar** novos equipamentos
- ✅ **Listar** todos os equipamentos
- ✅ **Editar** equipamentos existentes
- ✅ **Excluir** equipamentos
- ✅ **Visualizar** detalhes completos
- ✅ **Exportar** relatórios em Excel

### 🔧 Recursos Técnicos
- ✅ Validação de dados com Bean Validation
- ✅ Número de série único (constraint)
- ✅ Interface responsiva e moderna
- ✅ Mensagens flash para feedback
- ✅ Tratamento de erros
- ✅ Console H2 para debug

## 🛠️ Pré-requisitos

- **Java 21** ou superior
- **Maven 3.8+**
- **Git** (opcional)

## 🚀 Como Executar

### 1. **Clone o repositório**
```bash
git clone <url-do-repositorio>
cd cadastro-equipamentos/cadastro
```

### 2. **Compile e execute**
```bash
mvn clean install
mvn spring-boot:run
```

### 3. **Acesse a aplicação**
- **Sistema:** http://localhost:8080
- **Console H2:** http://localhost:8080/h2-console

### 4. **Configuração do H2 Console**
```
JDBC URL: jdbc:h2:mem:db
User: sa
Password: (deixe em branco)
```

## 📱 URLs da Aplicação

| Endpoint | Método | Descrição |
|----------|--------|-----------|
| `/` | GET | Página inicial (redireciona) |
| `/equipamentos` | GET | Lista todos os equipamentos |
| `/equipamentos/novo` | GET | Formulário de novo equipamento |
| `/equipamentos/salvar` | POST | Salva equipamento |
| `/equipamentos/editar/{id}` | GET | Formulário de edição |
| `/equipamentos/excluir/{id}` | GET | Exclui equipamento |
| `/equipamentos/detalhes/{id}` | GET | Detalhes do equipamento |
| `/equipamentos/exportar/excel` | GET | Download do relatório Excel |

## 📊 Modelo de Dados

### Entidade: Equipamento

| Campo | Tipo | Descrição | Validação |
|-------|------|-----------|-----------|
| `id` | Long | Identificador único | Auto-incremento |
| `numeroSerie` | String | Número de série | Obrigatório, único, 3-50 chars |
| `marca` | String | Marca do equipamento | Opcional, max 100 chars |
| `modelo` | String | Modelo do equipamento | Opcional, max 100 chars |
| `dataEntrega` | LocalDate | Data de entrega | Opcional |

## 🎨 Interface do Usuário

### 📋 Lista de Equipamentos
- Tabela responsiva com todos os equipamentos
- Botões de ação (Detalhes, Editar, Excluir)
- Contadores e estatísticas
- Botão para exportar Excel

### 📝 Formulário de Cadastro/Edição
- Validação em tempo real
- Campos obrigatórios marcados
- Feedback visual de erros
- Suporte a datas

### 📊 Relatório Excel
- Cabeçalho estilizado
- Formatação de datas
- Auto-ajuste de colunas
- Nome do arquivo: `equipamentos.xlsx`

## 🔧 Configurações

### application.properties
```properties
spring.application.name=cadastro
spring.h2.console.enabled=true
spring.datasource.url=jdbc:h2:mem:db
spring.jpa.hibernate.ddl-auto=update
server.port=8080
```

## Troubleshooting

### Problema: Erro 404
**Solução:** Verifique se está acessando http://localhost:8080/equipamentos

### Problema: Banco não inicializa
**Solução:** Verifique as configurações do H2 no application.properties

### Problema: Erro de compilação
**Solução:** 
```bash
mvn clean install -U
```

### Problema: Porta em uso
**Solução:** Altere a porta no application.properties:
```properties
server.port=8081
```

## 📈 Futuras Melhorias

- [ ] Autenticação e autorização
- [ ] Paginação na listagem
- [ ] Filtros e busca avançada
- [ ] Upload de imagens
- [ ] API REST
- [ ] Backup automático
- [ ] Notificações
- [ ] Dashboard com gráficos

## 🤝 Contribuição

1. Fork o projeto
2. Crie uma branch (`git checkout -b feature/nova-funcionalidade`)
3. Commit suas mudanças (`git commit -am 'Adiciona nova funcionalidade'`)
4. Push para a branch (`git push origin feature/nova-funcionalidade`)
5. Abra um Pull Request

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## 👥 Autor

Desenvolvido por Filipe Cândido Rodrigues

---

### 🔗 Links Úteis

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Thymeleaf Documentation](https://www.thymeleaf.org/documentation.html)
- [H2 Database](http://www.h2database.com/)
- [Apache POI](https://poi.apache.org/)
