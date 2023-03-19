package com.ssabi.springboottutorial.controller

import com.ssabi.springboottutorial.model.Bank
import com.ssabi.springboottutorial.service.BankService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/banks")
class BankController(private val service: BankService) {

  @ExceptionHandler(NoSuchElementException::class)
  fun handleNotFound(e: NoSuchElementException): ResponseEntity<String> =
    ResponseEntity(e.message, HttpStatus.NOT_FOUND)

  @ExceptionHandler(IllegalArgumentException::class)
  fun handleBadRequest(e: IllegalArgumentException): ResponseEntity<String> =
    ResponseEntity(e.message, HttpStatus.BAD_REQUEST)

  @GetMapping
  fun getBanks(): Collection<Bank> = service.getBanks()

  @GetMapping("/{accountNumber}")
  fun getBank(@PathVariable accountNumber: String): Bank = service.getBank(accountNumber)

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  fun addBank(@RequestBody bank: Bank): Bank = service.addBank(bank)

  @PatchMapping
  fun updateBank(@RequestBody bank: Bank): Bank = service.updateBank(bank)
}