package com.systempro.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.systempro.cursomc.domain.Categoria;
import com.systempro.cursomc.domain.Cidade;
import com.systempro.cursomc.domain.Cliente;
import com.systempro.cursomc.domain.Endereco;
import com.systempro.cursomc.domain.Estado;
import com.systempro.cursomc.domain.Produto;
import com.systempro.cursomc.domain.enums.TipoCliente;
import com.systempro.cursomc.domain.repositories.CategoriaRepository;
import com.systempro.cursomc.domain.repositories.CidadeRepository;
import com.systempro.cursomc.domain.repositories.ClienteRepository;
import com.systempro.cursomc.domain.repositories.EnderecoRepository;
import com.systempro.cursomc.domain.repositories.EstadoRepository;
import com.systempro.cursomc.domain.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Autowired
	private CategoriaRepository categoriaRepositori;

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	EnderecoRepository enderecoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");

		Produto p1 = new Produto(null, "Computador", 200.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		categoriaRepositori.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "São Paulo");
		Estado est2 = new Estado(null, "Minas Gerais");
		
		
		Cidade c1 = new Cidade(null, "Uberlandi", est2);
		Cidade c2 = new Cidade(null, "São Paulo", est1);
		Cidade c3 = new Cidade(null, "Campinas", est1);
		
		est1.getCidades().addAll(Arrays.asList(c2, c3));
		est2 .getCidades().addAll(Arrays.asList(c1));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "12312312311", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("1231-2311", "4444-5555"));
	  
		Endereco e1 = new Endereco(null, "Rua flor", "13", "apto15", "Cidade Líder", "12345-000", cli1, c1);
		Endereco e2 = new Endereco(null, "Rua Silva e Silva", "26", "sala 800", "Itaquera", "45612-000", cli1, c2);
	
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
	}

}
