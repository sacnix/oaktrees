import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NuevoRolComponent } from './nuevo-rol.component';

describe('NuevoRolComponent', () => {
  let component: NuevoRolComponent;
  let fixture: ComponentFixture<NuevoRolComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NuevoRolComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NuevoRolComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
