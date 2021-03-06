package com.dinerico.pos.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.dinerico.pos.R;
import com.dinerico.pos.db.InvoiceDB;
import com.dinerico.pos.exception.ValidationError;
import com.dinerico.pos.model.Account;
import com.dinerico.pos.model.Customer;
import com.dinerico.pos.model.Invoice;
import com.dinerico.pos.model.InvoiceResponse;
import com.dinerico.pos.model.RestErrorFactora;
import com.dinerico.pos.model.SigningInvoice;
import com.dinerico.pos.model.Order;
import com.dinerico.pos.model.Store;
import com.dinerico.pos.network.config.FactoraActivityBase;
import com.dinerico.pos.network.service.EmailService;
import com.dinerico.pos.network.service.InvoiceService;
import com.dinerico.pos.viewmodel.ReceiptViewModel;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import rx.android.Events;
import rx.functions.Action1;


public class ReceiptActivity extends FactoraActivityBase {

  private ReceiptViewModel viewModel;
  private ViewHolder view;

  private String backupCustomerid;
  private String backupName;

  private static String MESSAGE_TITTLE;



  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_receipt);
    setUpActionBar();
    MESSAGE_TITTLE = getString(R.string.receiptMessageTittle);
    viewModel = new ReceiptViewModel(new Invoice(),
            new Customer(), new InvoiceService(getSpiceManager()),
            new EmailService(getSpiceManager()),
            new InvoiceDB(this));
    view = new ViewHolder();

  }

  private void setUpActionBar() {
    hideActionBarComponents();
    View actionBar = getLayoutInflater().inflate(R.layout
                    .action_bar_simple_button,
            null);
    View actionContainer = actionBar.findViewById(R.id.actionContainer);
    actionContainer.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        generateInvoice();
      }
    });
    TextView action = (TextView) actionBar.findViewById(R.id.action);
    action.setText(getString(R.string.send));
    ImageView actionImg = (ImageView) actionBar.findViewById(R.id.actionImg);
    actionImg.setImageResource(R.drawable.arrow_right_white);
    getActionBar().setCustomView(actionBar);
  }

  private void finalConsumerOn() {
    viewModel.setFinalConsumer(true);

    backupCustomerid = view.customerId.getText().toString();
    backupName = view.names.getText().toString();

    view.telephoneNumber.setVisibility(View.INVISIBLE);
    view.address.setVisibility(View.INVISIBLE);
    view.customerId.setText(getString(R.string.finalConsumerRUC));
    view.names.setText(getString(R.string.finalConsumer));
    viewModel.setIdType(Customer.CONSUMIDOR_FINAL);

  }

  private void finalConsumerOff() {
    viewModel.setFinalConsumer(false);
    view.telephoneNumber.setVisibility(View.VISIBLE);
    view.address.setVisibility(View.VISIBLE);
    view.customerId.setText(backupCustomerid);
    view.names.setText(backupName);
  }

  private void generateInvoice() {
    try {
      viewModel.validate();



      Store store = Account.getInstance().getStore();
//      String keyInvoice = store.getClaveFacturacionElectronica();
      String keyInvoice = "497eacaa70f34afb86e343d568540cc5";

      String seqInvoice = "";
      if (Account.getInstance().getStore().getRazonSocial().equals
              ("Datilmedia S.A.") && Account.getInstance().getStore()
              .getInvoices().size() == 0)
        seqInvoice = "146";
      else
        seqInvoice = viewModel.sequentialInvoice();

      showProgressDialog();
      viewModel.createInvoiceOnSRI(keyInvoice, store.getCodigo(), seqInvoice,
              Order.getInstance(), new CreateInvoiceListener());

    } catch (ValidationError e) {
      showErrorValidation(e);
    }

  }

  private final class CreateInvoiceListener implements
          RequestListener<InvoiceResponse> {

    @Override
    public void onRequestFailure(SpiceException e) {
      dismissProgressDialog();
      showNetworkError(e, MESSAGE_TITTLE, RestErrorFactora.class);
    }

    @Override
    public void onRequestSuccess(InvoiceResponse invoiceResponse) {
//      String keyInvoice = Account.getInstance().getStore()
//              .getClaveFacturacionElectronica();
      String keyInvoice = "497eacaa70f34afb86e343d568540cc5";

      viewModel.signInvoice(
              new SigningInvoice(
                      keyInvoice,
                      invoiceResponse.getWeburl(),
                      SigningInvoice.PASSWORD,
                      SigningInvoice.DO_COMPLETE_INVOICE_PROCESS),
              new SigningInvoiceListener());
    }
  }

  private final class SigningInvoiceListener implements
          RequestListener<InvoiceResponse> {

    @Override
    public void onRequestFailure(SpiceException e) {
      dismissProgressDialog();
      showNetworkError(e, MESSAGE_TITTLE, RestErrorFactora.class);
    }

    @Override
    public void onRequestSuccess(InvoiceResponse invoiceResponse) {
      dismissProgressDialog();
      viewModel.createInvoiceOnDB();
      Intent intent = new Intent(ReceiptActivity.this,
              ReceiptSentActivity.class);
      startActivity(intent);
    }
  }

  private class ViewHolder {
    public EditText email;
    public EditText customerId;
    public EditText names;
    public EditText address;
    public EditText telephoneNumber;
    public Switch aSwitch;

    public ViewHolder() {
      findViews();
      subscribeToViewComponents();
    }

    private void findViews() {
      email = (EditText) findViewById(R.id.email);
      customerId = (EditText) findViewById(R.id.customerId);
      names = (EditText) findViewById(R.id.name);
      address = (EditText) findViewById(R.id.address);
      telephoneNumber = (EditText) findViewById(R.id.telephoneNumber);
      aSwitch = (Switch) findViewById(R.id.switch1);
      aSwitch.setOnCheckedChangeListener(new CompoundButton
              .OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton,
                                     boolean checked) {
          if (checked)
            finalConsumerOn();
          else
            finalConsumerOff();

        }
      });
    }

    private void subscribeToViewComponents() {

      Events.text(email).subscribe(new Action1<String>() {
        @Override
        public void call(String string) {
          viewModel.setEmail(string);
        }
      });
      Events.text(customerId).subscribe(new Action1<String>() {
        @Override
        public void call(String string) {
          viewModel.setCustomerId(string);
        }
      });
      Events.text(names).subscribe(new Action1<String>() {
        @Override
        public void call(String string) {
          viewModel.setNames(string);
        }
      });
      Events.text(address).subscribe(new Action1<String>() {
        @Override
        public void call(String string) {
          viewModel.setAddress(string);
        }
      });
      Events.text(telephoneNumber).subscribe(new Action1<String>() {
        @Override
        public void call(String string) {
          viewModel.setTelephoneNumber(string);
        }
      });

    }

  }
}
