package com.devsu.hackerearth.backend.client.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.devsu.hackerearth.backend.client.model.dto.ClientDto;
import com.devsu.hackerearth.backend.client.model.dto.PartialClientDto;
import com.devsu.hackerearth.backend.client.service.ClientService;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

	private final ClientService clientService;

	public ClientController(ClientService clientService) {
		this.clientService = clientService;
	}

	@GetMapping
	public ResponseEntity<List<ClientDto>> getAll() {
		// api/clients
		// Get all clients
		List<ClientDto> clients = clientService.getAll();
		if (!clients.isEmpty()) {
			return ResponseEntity.ok(clients);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClientDto> get(@PathVariable Long id) {
		// api/clients/{id}
		// Get clients by id
		ClientDto clientDto = clientService.getById(id);
		if (clientDto != null) {
			return ResponseEntity.ok(clientDto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<ClientDto> create(@RequestBody ClientDto clientDto) {
		// api/clients
		// Create client
		ClientDto createdClient = clientService.create(clientDto);
		return ResponseEntity.status(201).body(createdClient);
	}

	@PutMapping
	public ResponseEntity<ClientDto> update(@RequestBody ClientDto clientDto) {
		// api/clients/{id}
		// Update client
		ClientDto updatedClient = clientService.update(clientDto);
		if (updatedClient != null) {
			return ResponseEntity.ok(updatedClient);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PatchMapping("/{id}")
	public ResponseEntity<ClientDto> partialUpdate(@PathVariable Long id,
			@RequestBody PartialClientDto partialClientDto) {
		// api/accounts/{id}
		// Partial update accounts
		ClientDto updatedClient = clientService.partialUpdate(id, partialClientDto);
		if (updatedClient != null) {
			return ResponseEntity.ok(updatedClient);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		// api/clients/{id}
		// Delete client
		clientService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
