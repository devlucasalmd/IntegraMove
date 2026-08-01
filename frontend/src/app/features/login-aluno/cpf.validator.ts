import { AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';

/**
 * Valida CPF: verifica formato (11 dígitos), rejeita sequências repetidas
 * (ex: 111.111.111-11) e confere os dois dígitos verificadores.
 * Não trata campo vazio como erro — isso é responsabilidade do Validators.required.
 */
export function cpfValidator(): ValidatorFn {
  return (control: AbstractControl): ValidationErrors | null => {
    const raw = (control.value ?? '').toString();
    const cpf = raw.replace(/\D/g, '');

    if (!cpf) {
      return null;
    }

    if (cpf.length !== 11 || /^(\d)\1{10}$/.test(cpf)) {
      return { cpfInvalido: true };
    }

    if (!verificarDigitos(cpf)) {
      return { cpfInvalido: true };
    }

    return null;
  };
}

function verificarDigitos(cpf: string): boolean {
  let soma = 0;
  for (let i = 0; i < 9; i++) {
    soma += parseInt(cpf.charAt(i), 10) * (10 - i);
  }
  let resto = (soma * 10) % 11;
  if (resto === 10 || resto === 11) {
    resto = 0;
  }
  if (resto !== parseInt(cpf.charAt(9), 10)) {
    return false;
  }

  soma = 0;
  for (let i = 0; i < 10; i++) {
    soma += parseInt(cpf.charAt(i), 10) * (11 - i);
  }
  resto = (soma * 10) % 11;
  if (resto === 10 || resto === 11) {
    resto = 0;
  }
  return resto === parseInt(cpf.charAt(10), 10);
}
