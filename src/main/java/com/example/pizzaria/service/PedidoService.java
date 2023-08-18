package com.example.pizzaria.service;


import com.example.pizzaria.dto.ClienteDTO;
import com.example.pizzaria.dto.EnderecoDTO;
import com.example.pizzaria.dto.FuncionarioDTO;
import com.example.pizzaria.dto.PedidoDTO;
import com.example.pizzaria.entity.*;
import com.example.pizzaria.dto.ProdutoDiversoDTO;
import com.example.pizzaria.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PedidoService {


    @Autowired
    private PedidoRepository pedidoRepository;


    public List<PedidoDTO> findAll(){
        List<Pedido> pedidos = this.pedidoRepository.findAll();
        List<PedidoDTO> pedidosDTO = new ArrayList<>();

        for(Pedido i : pedidos)
        {
            pedidosDTO.add(convertToDTO(i));
        }

        return pedidosDTO;
    }

    public PedidoDTO findById(Long id)
    {
        Pedido pedido = this.pedidoRepository.findById(id).orElse(null);

        return pedido == null
                ? null
                : convertToDTO(pedido);
    }

    public void cadastrar(PedidoDTO pedidoDTO)
    {
        Assert.notNull(pedidoDTO.getCliente(), "Cliente não pode ser nulo");
        Assert.notNull(pedidoDTO.getAtendente(), "Atedente não pode ser nulo");
        Assert.notNull(pedidoDTO.isSolicitaEntrega(), "Solicitação de entrega não pode ser nula");
        Assert.notNull(pedidoDTO.getEndereco(), "Endereço não pode ser nulo");
        Assert.notNull(pedidoDTO.getPedido(), "Status do pedido não pode ser nulo");
        Assert.notNull(pedidoDTO.getValorTotal(), "Valor não pode ser nulo");
        Assert.notNull(pedidoDTO.getEntregador(), "Entregador não pode ser nulo");
        Assert.notNull(pedidoDTO.getFormaPagamento(), "Forma de pagamento não pode ser nulo");

        Pedido pedido = convertToEntity(pedidoDTO);

        this.pedidoRepository.save(pedido);
    }

    public void editar(PedidoDTO pedidoDTO, Long id)
    {
        Assert.isTrue(pedidoRepository.doesExist(id), "Pedido não existe");
        Assert.notNull(pedidoDTO.getCliente(), "Cliente não pode ser nulo");
        Assert.notNull(pedidoDTO.getAtendente(), "Atedente não pode ser nulo");
        Assert.notNull(pedidoDTO.isSolicitaEntrega(), "Solicitação de entrega não pode ser nula");
        Assert.notNull(pedidoDTO.getEndereco(), "Endereço não pode ser nulo");
        Assert.notNull(pedidoDTO.getPedido(), "Status do pedido não pode ser nulo");
        Assert.notNull(pedidoDTO.getValorTotal(), "Valor não pode ser nulo");
        Assert.notNull(pedidoDTO.getEntregador(), "Entregador não pode ser nulo");
        Assert.notNull(pedidoDTO.getFormaPagamento(), "Forma de pagamento não pode ser nulo");

        Pedido pedido = convertToEntity(pedidoDTO);

        this.pedidoRepository.save(pedido);
    }

    public void deletar(Long id)
    {
        Pedido pedido = this.pedidoRepository.findById(id).orElse(null);

        Assert.notNull(pedido, "Esse pedido não existe");

        pedido.setAtivo(false);
        this.pedidoRepository.save(pedido);
    }








    private Pedido convertToEntity(PedidoDTO pedidoDTO)
    {
        Pedido pedido = new Pedido();

        pedido.setCliente(convertToClienteEntity(pedidoDTO.getCliente()));
        pedido.setAtendente(convertToFuncionarioEntity(pedidoDTO.getAtendente()));
        pedido.setEndereco(convertToEnderecoEntity(pedidoDTO.getEndereco()));
        pedido.setSolicitaEntrega(pedidoDTO.isSolicitaEntrega());
        pedido.setPedido(pedidoDTO.getPedido());
        pedido.setValorTotal(pedidoDTO.getValorTotal());
        pedido.setEntregador(convertToFuncionarioEntity(pedidoDTO.getEntregador()));
        pedido.setFormaPagamento(pedidoDTO.getFormaPagamento());
        pedido.setEntrega(pedidoDTO.isEntrega());
        pedido.setDataPedido(pedidoDTO.getDataPedido());

        Set<ProdutoDiverso> produtos = new HashSet<>();

        for (ProdutoDiversoDTO i : pedidoDTO.getProdutos())
        {
            produtos.add(convertToProdutoEntity(i));
        }
        pedido.setProdutos(produtos);

        return pedido;
    }

    private PedidoDTO convertToDTO(Pedido pedido)
    {
        PedidoDTO pedidoDTO = new PedidoDTO();

        pedidoDTO.setId(pedido.getId());
        pedidoDTO.setCliente(convertToClienteDTO(pedido.getCliente()));
        pedidoDTO.setAtendente(convertToFuncionarioDTO(pedido.getAtendente()));
        pedidoDTO.setEndereco(convertToEnderecoDTO(pedido.getEndereco()));
        pedidoDTO.setSolicitaEntrega(pedido.isSolicitaEntrega());
        pedidoDTO.setPedido(pedido.getPedido());
        pedidoDTO.setValorTotal(pedido.getValorTotal());
        pedidoDTO.setEntregador(convertToFuncionarioDTO(pedido.getEntregador()));
        pedidoDTO.setFormaPagamento(pedido.getFormaPagamento());
        pedidoDTO.setEntrega(pedido.isEntrega());
        pedidoDTO.setDataPedido(pedido.getDataPedido());

        Set<ProdutoDiversoDTO> produtosDTO = new HashSet<>();

        for (ProdutoDiverso i : pedido.getProdutos())
        {
            produtosDTO.add(convertToProdutoDTO(i));
        }
        pedidoDTO.setProdutos(produtosDTO);

        return pedidoDTO;
    }


    private ProdutoDiverso convertToProdutoEntity(ProdutoDiversoDTO produtoDiversoDTO) {
        ProdutoDiverso produtoDiverso = new ProdutoDiverso();
        produtoDiverso.setTipo(produtoDiversoDTO.getTipo());
        produtoDiverso.setNome(produtoDiversoDTO.getNome());
        produtoDiverso.setPreco(produtoDiversoDTO.getPreco());
        produtoDiverso.setQuantidade(produtoDiversoDTO.getQuantidade());
        return produtoDiverso;
    }

    private ProdutoDiversoDTO convertToProdutoDTO(ProdutoDiverso produtoDiverso) {
        ProdutoDiversoDTO produtoDiversoDTO = new ProdutoDiversoDTO();
        produtoDiversoDTO.setTipo(produtoDiverso.getTipo());
        produtoDiversoDTO.setNome(produtoDiverso.getNome());
        produtoDiversoDTO.setPreco(produtoDiverso.getPreco());
        produtoDiversoDTO.setQuantidade(produtoDiverso.getQuantidade());
        return produtoDiversoDTO;
    }

    private FuncionarioDTO convertToFuncionarioDTO(Funcionario funcionario)
    {
        FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
        funcionarioDTO.setId(funcionario.getId());
        funcionarioDTO.setNome(funcionario.getNome());
        funcionarioDTO.setCpf(funcionario.getCpf());
        funcionarioDTO.setFuncao(funcionario.getFuncao());

        return funcionarioDTO;
    }

    private Funcionario convertToFuncionarioEntity(FuncionarioDTO funcionarioDTO)
    {
        Funcionario funcionario = new Funcionario();
        funcionario.setFuncao(funcionarioDTO.getFuncao());
        funcionario.setNome(funcionarioDTO.getNome());
        funcionario.setCpf(funcionarioDTO.getCpf());

        return funcionario;
    }

    private ClienteDTO convertToClienteDTO(Cliente cliente)
    {
        ClienteDTO clienteDTO = new ClienteDTO();

        clienteDTO.setId(cliente.getId());
        clienteDTO.setNome(cliente.getNome());
        clienteDTO.setCpf(cliente.getCpf());
        clienteDTO.setTelCelular(cliente.getTelCelular());

        return clienteDTO;
    }

    private Cliente convertToClienteEntity(ClienteDTO clienteDTO)
    {
        Cliente cliente = new Cliente();

        cliente.setCpf(clienteDTO.getCpf());
        cliente.setNome(clienteDTO.getNome());
        cliente.setTelCelular(clienteDTO.getTelCelular());

        return cliente;
    }

    private EnderecoDTO convertToEnderecoDTO(Endereco endereco)
    {
        EnderecoDTO enderecoDTO = new EnderecoDTO();

        enderecoDTO.setId(endereco.getId());
        enderecoDTO.setNuEndereco(endereco.getNuEndereco());
        enderecoDTO.setCep(endereco.getCep());
        enderecoDTO.setRua(endereco.getRua());
        enderecoDTO.setBairro(endereco.getBairro());
        enderecoDTO.setComplemento(endereco.getComplemento());
        enderecoDTO.setClienteDTO(convertToClienteDTO(endereco.getCliente()));

        return enderecoDTO;
    }

    private Endereco convertToEnderecoEntity(EnderecoDTO enderecoDTO)
    {
        Endereco endereco = new Endereco();

        endereco.setNuEndereco(enderecoDTO.getNuEndereco());
        endereco.setCep(enderecoDTO.getCep());
        endereco.setRua(enderecoDTO.getRua());
        endereco.setBairro(enderecoDTO.getBairro());
        endereco.setComplemento(enderecoDTO.getComplemento());
        endereco.setCliente(convertToClienteEntity(enderecoDTO.getClienteDTO()));

        return endereco;
    }
}
