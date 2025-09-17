# 🌐 FLIMA.DEV

Site pessoal e portfólio de **Felipe Lima**.  
Um projeto que reúne minhas experiências profissionais, acadêmicas e projetos de software em um só lugar, servindo como vitrine para recrutadores e clientes freelance.

---

## 🎯 Objetivo
- Mostrar experiências acadêmicas e profissionais  
- Exibir cursos finalizados e em andamento  
- Apresentar projetos concluídos ou em desenvolvimento  
- Permitir contato via formulário ou redes sociais (LinkedIn, GitHub)  

---

## 🛠️ Tecnologias Utilizadas
- **Frontend**: React + Vite  
- **Backend**: Java + Spring Boot  
- **Banco de Dados**: PostgreSQL  
- **Mensageria**: RabbitMQ  
- **Observabilidade**: Prometheus + Grafana  
- **Testes**: JUnit + TestContainers  
- **Containerização & Orquestração**: Docker + Kubernetes  

---

## 🏗️ Arquitetura
O projeto segue uma arquitetura modular com:
- **Frontend** desacoplado, consumindo API REST do backend  
- **Backend** em Spring Boot conectado ao PostgreSQL  
- **RabbitMQ** para mensageria e fila de processamento assíncrono  
- **Prometheus e Grafana** para métricas e monitoramento  
- **CI/CD com GitHub Actions**, integrando build, testes e deploy  
- Hospedagem em **servidor caseiro**, acessado com segurança via SSH  

---

## 🔄 CI/CD
O pipeline no GitHub Actions é responsável por:
1. Build e testes automatizados  
2. Criação e publicação das imagens Docker  
3. Deploy no servidor caseiro via SSH seguro  

---

## 🚀 Como Executar Localmente
```bash
# Clone o repositório
git clone https://github.com/xLima12/flima.dev.git

# Entre no diretório
cd flima.dev

# Backend - executar via Docker
docker compose up backend

# Frontend - iniciar localmente
cd frontend
npm install
npm run dev

## 📬 Contato
- 🌎 [flima.dev](https://flima.dev)  
- 💼 [LinkedIn](https://www.linkedin.com/in/felipe-lima-19873a14b/)  
- 💻 [GitHub](https://github.com/xLima12)  