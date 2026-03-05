import { Component } from '@angular/core';
import {
  ReactiveFormsModule,
  FormBuilder,
  Validators,
  FormGroup,
} from '@angular/forms';
import { AvaliacaoRequestDTO } from '../model/avaliacao.model';
import { MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatCardModule } from '@angular/material/card';

@Component({
  selector: 'app-avaliacao-form',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatSelectModule,
    MatCheckboxModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatCardModule,
  ],
  templateUrl: './avaliacao-form.component.html',
  styleUrls: ['./avaliacao-form.component.css'],
})
export class AvaliacaoFormComponent {
  avaliacaoForm: FormGroup;

  constructor(private fb: FormBuilder) {
    this.avaliacaoForm = this.fb.group({
      dataAvaliacao: ['', Validators.required],
      remadaBracoD: [null],
      remadaBracoE: [null],

      elevacaoLatD: [null],
      elevacaoLatE: [null],

      extensaoJoelhoD: [null],
      extensaoJoelhoE: [null],

      flexaoJoelhoD: [null],
      flexaoJoelhoE: [null],

      extensaoQuadrilD: [null],
      extensaoQuadrilE: [null],
    });
  }

  salvar() {
    if (this.avaliacaoForm.invalid) {
      return;
    }

    const formValue = this.avaliacaoForm.getRawValue();

    const avaliacao: AvaliacaoRequestDTO = {
      ...formValue,
      dataAvaliacao: this.formatarData(formValue.dataAvaliacao),
    };

    console.log('Dados enviados:', avaliacao);
  }

  private formatarData(data: Date): string {
    return data.toISOString().split('T')[0];
  }
}
