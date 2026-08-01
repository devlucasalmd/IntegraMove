import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatMenuModule } from '@angular/material/menu';
import { MatDividerModule } from '@angular/material/divider';
import { MatBadgeModule } from '@angular/material/badge';

interface Notificacao {
  titulo: string;
  descricao: string;
  icone: string;
}

@Component({
  standalone: true,
  selector: 'app-header-notifications',
  imports: [
    CommonModule,
    MatIconModule,
    MatButtonModule,
    MatMenuModule,
    MatDividerModule,
    MatBadgeModule,
  ],
  templateUrl: './header-notifications.component.html',
  styleUrls: ['./header-notifications.component.css'],
})
export class  HeaderNotificationsComponent {
  // Mock por enquanto — depois conecta num service real
  notificacoes: Notificacao[] = [
    { titulo: 'Novo aluno cadastrado', descricao: 'João Silva se cadastrou', icone: 'person_add' },
    { titulo: 'Pagamento pendente', descricao: 'Plano de Maria vence hoje', icone: 'payments' },
  ];
}
