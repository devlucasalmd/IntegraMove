import { Component } from '@angular/core';
import {
  ReactiveFormsModule,
  FormBuilder,
  Validators,
  FormGroup,
} from '@angular/forms';
import { AvaliacaoRequestDTO } from '../model/avaliacao.model';
import { AvaliacaoService } from '../../core/services/avaliacao.service';
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

  constructor(
    private fb: FormBuilder,
    private avaliacaoService: AvaliacaoService,
  ) {
    this.avaliacaoForm = this.fb.group({
      dataAvaliacao: ['', Validators.required],
      remadaBracoD: [null, Validators.required],
      remadaBracoE: [null, Validators.required],

      elevacaoLatD: [null, Validators.required],
      elevacaoLatE: [null, Validators.required],

      extensaoJoelhoD: [null, Validators.required],
      extensaoJoelhoE: [null, Validators.required],

      flexaoJoelhoD: [null, Validators.required],
      flexaoJoelhoE: [null, Validators.required],

      extensaoQuadrilD: [null, Validators.required],
      extensaoQuadrilE: [null, Validators.required],
    });
  }

  private formatarData(data: any): string {
    if(!data) return '';
    const date = new Date(data);

    return date.toLocaleDateString('sv-SE');
  }


  salvar() {
    if (this.avaliacaoForm.invalid) { return; }

    const formValue = this.avaliacaoForm.value;

    const avaliacao: AvaliacaoRequestDTO = {
      dataAvaliacao: this.formatarData(formValue.dataAvaliacao),
      remadaBracoD: formValue.remadaBracoD,
      remadaBracoE: formValue.remadaBracoE,
      elevacaoLatD: formValue.elevacaoLatD,
      elevacaoLatE: formValue.elevacaoLatE,
      extensaoJoelhoD: formValue.extensaoJoelhoD,
      extensaoJoelhoE: formValue.extensaoJoelhoE,
      flexaoJoelhoD: formValue.flexaoJoelhoD,
      flexaoJoelhoE: formValue.flexaoJoelhoE,
      extensaoQuadrilD: formValue.extensaoQuadrilD,
      extensaoQuadrilE: formValue.extensaoQuadrilE

    };

    console.log('Dados enviados:', avaliacao);

    this.avaliacaoService.cadastrarAvaliacao(avaliacao)
      .subscribe({
        next: (response) => {
          console.log("Avaliação salva com sucesso", response);
          this.avaliacaoForm.reset();
        },
        error: (err) => {
          console.error("Erro ao salvar avaliação", err);
        }

      });
  }
}
