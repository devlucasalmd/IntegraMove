import { Component, Inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { TIPOS_USUARIO, Usuario, UsuarioFormValue } from '../../model/usuarios.model';

@Component({
  selector: 'app-usuario-form-dialog',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule,
  ],
  templateUrl: './usuarios-form.component.html',
})
export class UsuariosFormComponent {
  form: FormGroup;
  tiposUsuario = TIPOS_USUARIO;

  constructor(
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<UsuariosFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { usuario: Usuario | null }
  ) {
    const usuario = data.usuario;

    this.form = this.fb.group({
      nome: [usuario?.nome ?? '', Validators.required],
      email: [usuario?.email ?? '', [Validators.required, Validators.email]],
      telefone: [usuario?.telefone ?? '', Validators.required],
      tipo: [usuario?.tipo ?? 'recepcao', Validators.required],
    });
  }

  salvar(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    this.dialogRef.close(this.form.value as UsuarioFormValue);
  }

  cancelar(): void {
    this.dialogRef.close(null);
  }
}
