import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditarPedidoAdminComponent } from './editar-pedido-admin.component';

describe('EditarPedidoAdminComponent', () => {
  let component: EditarPedidoAdminComponent;
  let fixture: ComponentFixture<EditarPedidoAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditarPedidoAdminComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditarPedidoAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
