<!--<form name= "applicationForm" id= "applicationForm" method= "post" action="ApplicationFormServlet">
    <h2>Loan Bazaar</h2>
    <table class="application_table">
        <tr class="application_tr">
            <th class="application_th">Prepared By</th>
            <th class="application_th">Contact</th>
            <th class="application_th">Date</th>
        </tr>
        <tr class="application_tr">
            <td class="application_td">Loan Bazaar</td>
            <td class="application_td">8982566683</td>
            <td class="application_td">20/04/2000</td>
        </tr>
    </table>
    <br>
    <h3 class="head_three"> Enter your CIBIL score:<input type="number" name="cibil"/></h3>
    <h3 class="head_three">Bank Account number:<input type="text" name="bank_acc_no"/></h3>
    <h3 class="head_three">Total Revenue Generation: <input type="number" name ="total_revenue_generation"/></h3>
    <h3 class="head_three">Loan Amount:<input type="number" name="loanamount"/></h3>
    <h3 class="head_three">Period: <input type="number" name="period"/></h3>
    <h3 class="head_three">Rate%: <input type="number" name="rate"/></h3>
    <h3 class="head_three">Total Employee expenses: <input type="number" name="total_employee_expense"/></h3>
    <h3 class="head_three" id="applicationFormSubmitBtn">  <input type="submit" value="Submit"/></h3>
    <br>
    <div class="container text-center" id="applicationFormLoader" style="display : none">
        <span class="fa fa-refresh fa-spin fa-4x" ></span>
        <h4> Please wait...</h4>
    </div>
</form>-->

<br>

<div class="container" id="applicationContainer">
    <div id="applicationFormPanel">
        <div class="panel">
            <h2>Loan Application Form</h2>
            <p>Please enter your details</p>
        </div>
        <!--<form name= "applicationForm" id= "applicationForm" method= "post" action="SubmitFormServlet">-->
        <form name= "applicationForm" id= "applicationForm" method= "post" action="ApplicationFormServlet">

            <br>
            <label for="cibil"><b>Enter your cibil score</b></label>
            <input type="number" class="applicationNumber" placeholder="Enter score in numbers" name="cibil" id="cibil" required>


            <label for="acc"><b>Bank Account Number</b></label>
            <input type="text" class="applicationText" placeholder="Enter bank account number" name="bank_acc_no" id="bank_acc_no" required>


            <label for="trg"><b>Total Revenue Generation</b></label>
            <input type="number" class="applicationNumber" placeholder="amount" name="total_revenue_generation" id="total_revenue_generation" required>


            <label for="loanamount"><b>Loan Amount</b></label>
            <input type="number" class="applicationNumber" placeholder="loan amount" name="loanamount" id="loanamount" required>


            <label for="period"><b>Period(in months)</b></label>
            <input type="number" class="applicationNumber" placeholder="enter time" name="period" id="period" required>

            <!--            <label for="rate"><b>Rate%</b></label>
                        <input type="number" class="applicationNumber" placeholder="enter rate" name="rate" id="rate" required>-->

            <label for="rate"><b>Ask for Discount</b></label>
            <input type="number" class="applicationNumber" placeholder="enter the discount percent" name="discount" id="discount" required>

            <label for="rate"><b>Employee expenses</b></label>
            <input type="number" class="applicationNumber" placeholder="enter Employee expenses"  name="total_employee_expense" id="total_employee_expense" required>

            <!--            <h3 class="head_three"> Enter your CIBIL score:<input type="number" name="cibil"/></h3>
                        <h3 class="head_three">Bank Account number:<input type="text" name="bank_acc_no"/></h3>
                        <h3 class="head_three">Total Revenue Generation: <input type="number" name ="total_revenue_generation"/></h3>
                        <h3 class="head_three">Loan Amount:<input type="number" name="loanamount"/></h3>
                        <h3 class="head_three">Period: <input type="number" name="period"/></h3>
                        <h3 class="head_three">Rate%: <input type="number" name="rate"/></h3>
                        <h3 class="head_three">Total Employee expenses: <input type="number" name="total_employee_expense"/></h3>-->
            <!--<h3 class="head_three" id="applicationFormSubmitBtn">-->
            <!--            <input type="submit" value="Submit" id="applicationFormSubmitBtn"/>-->
            <!--</h3>-->
            <br>
            <br>
            <div class="container text-center">
                <button type="submit" class="btn primary-background text-white" id="applicationFormSubmitBtn">Submit</button>

            </div>

            <br>
            <div class="container text-center" id="applicationFormLoader" style="display : none">
                <span class="fa fa-refresh fa-spin fa-4x" ></span>
                <h4> Please wait...</h4>
            </div>
        </form>
    </div>
</div>


<!--<div class="container" id="applicationContainer">
    <div class="Register-form" id="applicationFormPanel">
        <div class="panel">
            <h2>Loan Application Form</h2>
            <p>Please enter your details</p>
        </div>

        <form name="applicationForm" method="post" action="ApplicationFormServlet">



            <label for="cibil"><b>Enter your civil score</b></label>
            <input type="number" class="applicationNumber" placeholder="Enter score in numbers" name="cibil" id="cibil" required>


            <label for="acc"><b>Bank Account Number</b></label>
            <input type="text" class="applicationText" placeholder="Enter bank account number" name="bank_acc_no" id="bank_acc_no" required>


            <label for="trg"><b>Total Revenue Generation</b></label>
            <input type="number" class="applicationNumber" placeholder="amount" name="total_revenue_generation" id="total_revenue_generation" required>


            <label for="loanamount"><b>Loan Amount</b></label>
            <input type="number" class="applicationNumber" placeholder="loan amount" name="loanamount" id="loanamount" required>


            <label for="period"><b>Period</b></label>
            <input type="number" class="applicationNumber" placeholder="enter time" name="period" id="period" required>

            <label for="rate"><b>Rate%</b></label>
            <input type="number" class="applicationNumber" placeholder="enter rate" name="rate" id="rate" required>

            <label for="rate"><b>Employee expenses</b></label>
            <input type="number" class="applicationNumber" placeholder="enter Employee expenses"  name="total_employee_expense" id="total_employee_expense" required>


            <button type="submit" class="btn btn-primary" id="applicationFormSubmitBtn">Submit</button>


            <br>
            <div class="container text-center" id="applicationFormLoader" style="display : none">
                <span class="fa fa-refresh fa-spin fa-4x" ></span>
                <h4> Please wait...</h4>
            </div>


        </form>
    </div>
</div>-->
