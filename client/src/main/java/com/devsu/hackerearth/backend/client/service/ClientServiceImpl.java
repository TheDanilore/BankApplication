package com.devsu.hackerearth.backend.client.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.devsu.hackerearth.backend.client.model.Client;
import com.devsu.hackerearth.backend.client.model.dto.ClientDto;
import com.devsu.hackerearth.backend.client.model.dto.PartialClientDto;
import com.devsu.hackerearth.backend.client.repository.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService {

	private final ClientRepository clientRepository;

	public ClientServiceImpl(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	@Override
	public List<ClientDto> getAll() {
		// Get all clients
		List<Client> clients = clientRepository.findAll();
		return clients.stream()
				.map(client -> new ClientDto(client.getId(), client.getDni(), client.getName(), client.getPassword(),
						client.getGender(), client.getAge(), client.getAddress(), client.getPhone(), client.isActive()))
				.collect(Collectors.toList());
	}

	@Override
	public ClientDto getById(Long id) {
		// Get clients by id
		Optional<Client> clientOptional = clientRepository.findById(id);
		return clientOptional
				.map(client -> new ClientDto(client.getId(), client.getDni(), client.getName(), client.getPassword(),
						client.getGender(), client.getAge(), client.getAddress(), client.getPhone(), client.isActive()))
				.orElse(null);
	}

	@Override
	public ClientDto create(ClientDto clientDto) {
		// Create client
		Client client = new Client(clientDto.getId(), clientDto.getName(), clientDto.getDni(), clientDto.getGender(),
				clientDto.getAge(), clientDto.getAddress(), clientDto.getPhone(),
				clientDto.getPassword(), clientDto.isActive());

		Client savedClient = clientRepository.save(client);
		return new ClientDto(savedClient.getId(), savedClient.getDni(), savedClient.getName(),
				savedClient.getPassword(), savedClient.getGender(),
				savedClient.getAge(), savedClient.getAddress(), savedClient.getPhone(),
				savedClient.isActive());
	}

	@Override
	public ClientDto update(ClientDto clientDto) {
		// Update client
		Optional<Client> clientOptional = clientRepository.findById(clientDto.getId());
		if (clientOptional.isPresent()) {
			Client client = clientOptional.get();
			client.setDni(clientDto.getDni());
			client.setName(clientDto.getName());
			client.setPassword(clientDto.getPassword());
			client.setGender(clientDto.getGender());
			client.setAge(clientDto.getAge());
			client.setAddress(clientDto.getAddress());
			client.setPhone(clientDto.getPhone());
			client.setActive(clientDto.isActive());

			Client updatedClient = clientRepository.save(client);
			return new ClientDto(updatedClient.getId(), updatedClient.getDni(), updatedClient.getName(),
					updatedClient.getPassword(), updatedClient.getGender(),
					updatedClient.getAge(), updatedClient.getAddress(), updatedClient.getPhone(),
					updatedClient.isActive());
		}
		return null;
	}

	@Override
	public ClientDto partialUpdate(Long id, PartialClientDto partialClientDto) {
		// Partial update account
		Optional<Client> clientOptional = clientRepository.findById(id);
		if (clientOptional.isPresent()) {
			Client client = clientOptional.get();

			// Actualiza solo el campo
			if (partialClientDto.isActive() != false) {
				client.setActive(partialClientDto.isActive());
			}
			Client updatedClient = clientRepository.save(client);
			return new ClientDto(updatedClient.getId(), updatedClient.getDni(), updatedClient.getName(),
					updatedClient.getPassword(), updatedClient.getGender(),
					updatedClient.getAge(), updatedClient.getAddress(), updatedClient.getPhone(),
					updatedClient.isActive());
		}

		return null;
	}

	@Override
	public void deleteById(Long id) {
		// Delete client
		clientRepository.deleteById(id);
	}
}
