package br.com.rchlo.store.controller;

import br.com.rchlo.store.controller.form.PaymentForm;
import br.com.rchlo.store.controller.handlers.ErroFormDto;
import br.com.rchlo.store.domain.Payment;
import br.com.rchlo.store.domain.PaymentStatus;
import br.com.rchlo.store.dto.PaymentDto;
import br.com.rchlo.store.repository.PaymentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentRepository paymentRepository;

    public PaymentController(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDto> detail(@PathVariable Long id){

        Optional<Payment> payment = paymentRepository.findById(id);
        if (payment.isPresent()){
            return ResponseEntity.ok(new PaymentDto(payment.get()));
        }
        return ResponseEntity.notFound().build();

    }

    @PostMapping
    public ResponseEntity<PaymentDto> creatPayment(@RequestBody @Valid PaymentForm form, UriComponentsBuilder uriBuilder){
        if(!form.dateValidation()){
            return ResponseEntity.badRequest().build();
        }
        Payment payment = form.convert();
        paymentRepository.save(payment);

        URI uri = uriBuilder.path("/payments/{id}").buildAndExpand(payment.getId()).toUri();
        return ResponseEntity.created(uri).body(new PaymentDto(payment));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<PaymentDto> confirm(@PathVariable Long id){
        Optional<Payment> optional = paymentRepository.findById(id);
        if (optional.isPresent()){
            optional.get().setStatus(PaymentStatus.CONFIRMED);
            return ResponseEntity.ok(new PaymentDto(optional.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<PaymentDto> cancel(@PathVariable Long id){
        Optional<Payment> optional = paymentRepository.findById(id);
        if (optional.isPresent()){
            optional.get().setStatus(PaymentStatus.CANCELED);
            return ResponseEntity.ok(new PaymentDto(optional.get()));
        }
        return ResponseEntity.notFound().build();
    }


}
