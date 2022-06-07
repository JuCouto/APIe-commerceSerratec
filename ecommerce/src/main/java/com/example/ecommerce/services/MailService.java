package com.example.ecommerce.services;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.ecommerce.dtos.ItemPedidoDTO;
import com.example.ecommerce.dtos.PedidoDTO;

@Service
public class MailService {

	@Autowired
	JavaMailSender emailSender;

	@Value("${spring.mail.host}")
	private String mailHost;

	@Value("${spring.mail.port}")
	private String mailPort;

	@Value("${spring.mail.username}")
	private String mailUserName;

	@Value("${spring.mail.password}")
	private String mailPassword;

	public MailService(JavaMailSender emailSender) {
		this.emailSender = emailSender;
	}

	public void enviarEmailTexto(String destinatarioEmail, String assunto, String mensagemEmail, PedidoDTO pedidoDTO) {
		String c = montarCorpo(pedidoDTO);
		try {
			MimeMessage mail = emailSender.createMimeMessage();

			MimeMessageHelper helper = new MimeMessageHelper(mail);
			helper.setTo(destinatarioEmail);
			helper.setSubject("Teste Envio de e-mail");
			helper.setText(c, true);
			emailSender.send(mail);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String montarCorpo(PedidoDTO pedidoDTO) {

		StringBuilder builder = new StringBuilder();
		builder.append("<h1 style='color: green'>Pedido Finalizado com Sucesso</h1>")
				.append("<p><b>Número do pedido: </b>").append(pedidoDTO.getIdPedido()).append("<br>")
				.append("<b> Data pedido: </b>").append(pedidoDTO.getDataPedido()).append("<br>")
				.append(" <b>Data entrega: </b>").append(pedidoDTO.getDataEntrega()).append("<br>")
				.append("<b> Data envio: </b> ").append(pedidoDTO.getDataEnvio()).append("<br>")
				.append("<b> Status pedido: </b> ").append(pedidoDTO.getStatusPedido()).append("<br>")
				.append("<b>Total: </b>").append(pedidoDTO.getTotal()).append("</p>")
				.append("<h3 style='color: blue'>Informações do Cliente</h3>")
				.append("<p><b> Nome: </b>").append(pedidoDTO.getClienteDTO().getNomeCliente()).append("<br>")
				.append("<b>Email: </b>").append(pedidoDTO.getClienteDTO().getEmailCliente()).append("<br>")
				.append("<b> Cpf: </b> ").append(pedidoDTO.getClienteDTO().getCpfCliente()).append("<br>")
				.append("<b> Telefone: </b> ").append(pedidoDTO.getClienteDTO().getTelefoneCliente()).append("<br>")
				.append("<b> Cep: </b>").append(pedidoDTO.getClienteDTO().getEnderecoDTO().getCep()).append("<br>")
				.append("<b> Rua: </b> ").append(pedidoDTO.getClienteDTO().getEnderecoDTO().getRua()).append("<br>")
				.append("<b> Bairro: </b> ").append(pedidoDTO.getClienteDTO().getEnderecoDTO().getBairro()).append("<br>")
				.append("<b>Cidade: </b> ").append(pedidoDTO.getClienteDTO().getEnderecoDTO().getCidade()).append("<br>")
				.append("<b> Numero: </b> ").append(pedidoDTO.getClienteDTO().getEnderecoDTO().getNumero()).append("<br>")
				.append("<b> Complemento: </b> ").append(pedidoDTO.getClienteDTO().getEnderecoDTO().getComplemento()).append("<br>")
				.append("<b> Uf: </b> ").append(pedidoDTO.getClienteDTO().getEnderecoDTO().getUf()).append("</p>")
				.append("<h3 style='color: blue'>Pedido(s):</h3>");
		
		for (ItemPedidoDTO item : pedidoDTO.getItemPedidoDTO()) {
			builder.append("<p><b> Informações do produto:  </b>").append("</p>").append("<p><b> Produto: </b>")
					.append(item.getProdutoDTO().getNomeProduto()).append("<br>").append("<b> Descrição: </b>")
					.append(item.getProdutoDTO().getDescricaoProduto()).append("<br>").append("<b> Quantidade: </b>")
					.append(item.getProdutoDTO().getQtdEstoque()).append("<br>").append("<b> Valor Unitário: </b>")
					.append(item.getProdutoDTO().getValorUnitario()).append("<br>").append("<b> Valor Bruto: </b>")
					.append(item.getValorBruto()).append("<br>").append("<b> Desconto Aplicado: </b>")
					.append(item.getPercentualDesconto()).append("<br>").append("<b> Valor Final: </b>")
					.append(item.getValorLiquido()).append("</p>");
		}
		return builder.toString();
	}

}
